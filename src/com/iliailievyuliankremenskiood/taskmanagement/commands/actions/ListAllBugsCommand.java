package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FilterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllBugsCommand implements Command {
    /**
     * Command format: List_All_Bugs {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String NO_BUGS_ERROR = "There are currently no Bugs with STATUS: %s and ASSIGNEE: %s";
    private static final String BUGS_HEADER = "Bugs with STATUS: %s and ASSIGNEE: %s";
    private static final String SEPARATOR = "-".repeat(14);

    private final TeamManagementRepository teamManagementRepository;

    public ListAllBugsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String statusFilter = parameters.get(0);
        String assigneeFilter = parameters.get(1);
        List<Bug> bugList = teamManagementRepository.getBugs();
        bugList.sort(Comparator.comparing(
                Bug::getTitle)
                .thenComparing(Bug::getPriority)
                .thenComparing(Bug::getSeverity)
        );
        bugList = FilterHelpers.filterBugsByStatus(
                statusFilter,
                bugList,
                String.format(NO_BUGS_ERROR,statusFilter,assigneeFilter)
        );
        bugList = FilterHelpers.filterBugsByAssignee(
                assigneeFilter,
                bugList,
                String.format(NO_BUGS_ERROR,statusFilter,assigneeFilter)
        );
        if (bugList.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_BUGS_ERROR,statusFilter,assigneeFilter));
        }
        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(String.format(BUGS_HEADER, statusFilter, assigneeFilter)).append(System.lineSeparator());
        for (Bug bug : bugList) {
            output.append(bug.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
