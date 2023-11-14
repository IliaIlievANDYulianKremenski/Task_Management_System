package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;

import java.util.List;

public class UnassignBugCommand implements Command {

    /*TODO Yuli do you like this message for unassignment and we should think how to print bugs or stories that are
    not assigned. I mean if they have no assignee, they will print null. We have to avoid that and think how to print "no assignee" for example.
     */

    /** Command format: Unassign_Bug {bug ID} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String BUG_UNASSIGN_MESSAGE = "Bug with ID #%d is now unassigned.";

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public UnassignBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }
    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {

        int bugId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Bug ID"
        );

        Bug bug = teamManagementRepository.findBugById(bugId);

        /* TODO */
        bug.changeAssignee(null);

        return userOutput(bug);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Bug bug) {
        return String.format(BUG_UNASSIGN_MESSAGE, bug.getId());
    }
}