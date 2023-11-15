package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllBugsCommand implements Command {

    /** Command format: List_All_Bugs {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String NO_BUGS_ERROR = "There are currently no Bugs.";
    public static final String BUGS_HEADER = "Bugs: ";
    public static final String SEPARATOR = "-".repeat(14);


    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ListAllBugsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    /* TODO We can print the result as point the two filters as a header. For example ** Bugs with parameters: ALL_STATUSES ALL_ASSIGNEES: ** */

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String statusFilter = parameters.get(0);
        String assigneeFilter = parameters.get(1);
        List<Bug> bugList = teamManagementRepository.getBugs();
        List<Bug> filteredBugList = new ArrayList<>();

        filterBugsByStatus(statusFilter, bugList, filteredBugList);
        bugList.retainAll(filteredBugList);
        filterBugsByAssignee(statusFilter, bugList, assigneeFilter, filteredBugList);

        if (filteredBugList.isEmpty()) {
            throw new IllegalArgumentException(NO_BUGS_ERROR);
        }

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(BUGS_HEADER).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : filteredBugList) {
            output.append(bug.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    /*<-------Helper Method(s)------->*/

    /* TODO Do we want our filter to be case sensitive or to be able to find no matter upper or lower case. The other option is to parse the enum filter
    *   and search all bugs by their specific enum and that way we can implement to return an exception message to the user that the enum is incorrect and
    * "Should be ACTIVE or DONE.   */

    private static void filterBugsByStatus(String statusFilter, List<Bug> bugList, List<Bug> filteredBugList) {
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Bug bug : bugList) {
                if(bug.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        }
    }
    private static void filterBugsByAssignee(String statusFilter, List<Bug> bugList, String assigneeFilter, List<Bug> filteredBugList) {
        if (!statusFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
            for (Bug bug : bugList) {
                if(bug.getAssignee().toString().toUpperCase().contains(assigneeFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        }
    }
}
