package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowTaskActivityCommand implements Command {

    /** Command format: Show_Task_Activity {task ID} */


    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String ACTIVITY_HISTORY_HEADER = "Task #%d activity history: ";
    private static final String SEPARATOR = "-".repeat(14);

    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/
    public ShowTaskActivityCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Task ID"
        );
        Task task = teamManagementRepository.findTaskById(taskId);

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(String.format(ACTIVITY_HISTORY_HEADER,taskId)).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (String activity : task.getActivityHistory()) {
            output.append(activity).append(System.lineSeparator());
        }
        output.append(SEPARATOR).append(System.lineSeparator());
        return output.toString().trim();
    }
}
