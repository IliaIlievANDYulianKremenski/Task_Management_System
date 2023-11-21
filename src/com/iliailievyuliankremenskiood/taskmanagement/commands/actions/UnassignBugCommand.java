package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class UnassignBugCommand implements Command {
    /**
     * Command format: Unassign_Bug {bug ID}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String BUG_UNASSIGN_MESSAGE = "Bug with ID #%d is now not assigned.";
    private final TeamManagementRepository teamManagementRepository;

    public UnassignBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Bug ID"
        );
        Bug bug = teamManagementRepository.findBugById(bugId);
        bug.changeAssignee(null);
        return userOutput(bug);
    }

    private static String userOutput(Bug bug) {
        return String.format(BUG_UNASSIGN_MESSAGE, bug.getId());
    }
}