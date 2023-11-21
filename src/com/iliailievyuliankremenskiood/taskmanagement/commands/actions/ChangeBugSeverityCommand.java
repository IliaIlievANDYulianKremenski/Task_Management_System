package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugSeverityCommand implements Command {
    /**
     * Command format: Change_Bug_Severity {bug ID} {new severity}
     */
    private static final String INVALID_BUG_SEVERITY_MESSAGE =
            "Invalid value for Bug Severity: %s. Should be CRITICAL, MAJOR or MINOR.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TeamManagementRepository teamManagementRepository;

    public ChangeBugSeverityCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.parseInteger(parameters.get(0), "Bug ID");
        BugSeverityType bugSeverityType = ParsingHelpers.parseEnum(
                parameters.get(1),
                BugSeverityType.class,
                INVALID_BUG_SEVERITY_MESSAGE
        );
        Bug bug = teamManagementRepository.findBugById(bugId);
        bug.changeBugSeverity(bugSeverityType);
        return userOutput(bug);
    }

    private static String userOutput(Bug bug) {
        return bug.getActivityHistory().get(bug.getActivityHistory().size() - 1);
    }
}