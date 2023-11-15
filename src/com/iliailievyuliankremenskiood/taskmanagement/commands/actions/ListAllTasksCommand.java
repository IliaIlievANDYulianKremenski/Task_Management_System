package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;

import java.util.List;

public class ListAllTasksCommand implements Command {
    /**
     * Command format: List_All_Tasks
     * */

    /*<-------Constant(s)------->*/
    public static final String NO_TASKS_ERROR = "There are no Tasks (Bugs, Stories or Feedbacks).";


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ListAllTasksCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        if (teamManagementRepository.getTasks().isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        }

        StringBuilder result = new StringBuilder();
        for (Task task : teamManagementRepository.getTasks()) {
            result.append(task.print()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
