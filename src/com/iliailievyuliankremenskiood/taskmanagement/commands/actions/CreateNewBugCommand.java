package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBugCommand implements Command {

    /** Command format: Create_New_Bug {title} {description} {priority} {severity} {assignee} */

    /*<-------Constant(s)------->*/
    private static final String INVALID_BUG_PRIORITY_MESSAGE =
            "Invalid value for Bug Priority: %s. Should be HIGH, MEDIUM or LOW.";
    private static final String INVALID_BUG_SEVERITY_MESSAGE =
            "Invalid value for Bug Severity: %s. Should be CRITICAL, MAJOR or MINOR.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public CreateNewBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        BugPriorityType bugPriorityType = ParsingHelpers.parseEnum(
                parameters.get(2),
                BugPriorityType.class,
                String.format(INVALID_BUG_PRIORITY_MESSAGE)
        );
        BugSeverityType bugSeverityType = ParsingHelpers.parseEnum(
                parameters.get(3),
                BugSeverityType.class,
                String.format(INVALID_BUG_SEVERITY_MESSAGE)
        );
        String assigneeName = parameters.get(4);

        Member assignee = teamManagementRepository.findMemberByName(assigneeName);
        Bug bug = teamManagementRepository.createBug(
                title,
                description,
                bugPriorityType,
                bugSeverityType,
                assignee);

        return userOutput(bug);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Bug bug) {
        return bug.getActivityHistory().get(bug.getActivityHistory().size() - 1);
    }
}