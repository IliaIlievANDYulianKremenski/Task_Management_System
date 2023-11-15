package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;

import java.util.List;

public class ListAllTasksCommand implements Command {
    /**
     * Command format: List_All_Tasks {filter title / ALL_TITLES}
     * */

    /*<-------Constant(s)------->*/
    public static final String NO_TASKS_ERROR = "There are no Tasks (Bugs, Stories or Feedbacks) to be listed.";
    public static final String TASKS_HEADER = "Tasks: ";
    public static final String SEPARATOR = "-".repeat(14);

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

        /*✏️ TODO ✏️- for Yuli to implement this part of the function.*/
        /*switch (parameters.size()){
            case 0 *//*All Tasks*//*:
                break;
            case 1 *//*Filter by title and sort by title*//*:
                break;
        }*/

        StringBuilder result = new StringBuilder();
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Task task : teamManagementRepository.getTasks()) {
            result.append(task.print()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
