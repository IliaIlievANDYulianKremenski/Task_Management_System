package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugPriorityCommand implements Command {

    /** Command format: Change_Bug_Priority {bug ID} {new priority} */

    /*<-------Constant(s)------->*/
    private static final String INVALID_BUG_PRIORITY_MESSAGE = "Invalid value for Bug Priority: %s. Should be HIGH, MEDIUM or LOW.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ChangeBugPriorityCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int bugId = ParsingHelpers.parseInteger(parameters.get(0),"Bug ID");
        BugPriorityType bugPriorityType = ParsingHelpers.parseEnum(
                parameters.get(1),
                BugPriorityType.class,
                String.format(INVALID_BUG_PRIORITY_MESSAGE)
        );

        Bug bug = teamManagementRepository.findBugById(bugId);

        bug.changeBugPriority(bugPriorityType);

        return userOutput(bug);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Bug bug) {
        return bug.getActivityHistory().get(bug.getActivityHistory().size() - 1);
    }
}
