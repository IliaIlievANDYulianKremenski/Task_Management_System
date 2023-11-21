package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowStepsToReproduceCommand implements Command {
    /**
     * Command format: Show_Steps_To_Reproduce {bug ID}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String BUG_STEPS_TO_REPRODUCE_MESSAGE = "Bug ID: %d, steps to reproduce:";
    public static final String SEPARATOR = "--------------";

    private final TeamManagementRepository teamManagementRepository;

    public ShowStepsToReproduceCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.parseInteger(parameters.get(0), "Bug Id");
        Bug bug = teamManagementRepository.findBugById(bugId);
        List<String> stepsToReproduce = bug.getStepsToReproduce();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(BUG_STEPS_TO_REPRODUCE_MESSAGE, bugId))
                .append(System.lineSeparator())
                .append(SEPARATOR)
                .append(System.lineSeparator());

        for (int i = 0; i < stepsToReproduce.size(); i++) {
            sb.append(stepsToReproduce.get(i)).append(System.lineSeparator());
        }
        sb.append(SEPARATOR);
        return sb.toString();
    }
}
