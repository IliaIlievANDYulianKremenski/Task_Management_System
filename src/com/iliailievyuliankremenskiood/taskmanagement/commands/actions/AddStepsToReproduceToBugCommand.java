package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddStepsToReproduceToBugCommand implements Command {
    /**
     * Command format: Add_Step_To_Reproduce_To_Bug {step to reproduce} {Bug Id}
     * */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TeamManagementRepository teamManagementRepository;

    public AddStepsToReproduceToBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String[] stepsToReproduce = parameters.get(0).split(";");
        int bugId = ParsingHelpers.parseInteger(parameters.get(1),"Bug Id");
        Bug bug = teamManagementRepository.findBugById(bugId);
        for (int i = 0; i < stepsToReproduce.length; i++) {
            bug.addStepToReproduce(stepsToReproduce[i]);
        }
        return userOutput(bug);
    }

    private static String userOutput(Bug bug) {
        return bug.getActivityHistory().get(bug.getActivityHistory().size() - 1);
    }
}
