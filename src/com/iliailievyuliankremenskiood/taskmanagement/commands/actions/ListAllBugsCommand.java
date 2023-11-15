package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FilterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllBugsCommand implements Command {

    /** Command format: List_All_Bugs {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String NO_BUGS_ERROR = "There are currently no Bugs.";
    private static final String BUGS_HEADER = "Bugs: ";
    private static final String SEPARATOR = "-".repeat(14);


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

        List<Bug> filteredBugList = FilterHelpers.filterBugsByStatus(
                statusFilter,
                bugList);
        filteredBugList = FilterHelpers.filterBugsByAssignee(
                assigneeFilter,
                new ArrayList<>(filteredBugList)
                );

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


    /* TODO Do we want our filter to be case sensitive or to be able to find no matter upper or lower case. The other option is to parse the enum filter
    *   and search all bugs by their specific enum and that way we can implement to return an exception message to the user that the enum is incorrect and
    * "Should be ACTIVE or DONE.   */

}
