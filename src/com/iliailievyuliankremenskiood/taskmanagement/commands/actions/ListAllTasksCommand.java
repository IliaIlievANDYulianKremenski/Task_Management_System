package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllTasksCommand implements Command {
    /**
     * Command format: List_All_Tasks {filter title / ALL_TITLES}
     */
    public static final String NO_TASKS_ERROR = "There are no Tasks (Bugs, Stories or Feedbacks) to be listed.";
    public static final String TASKS_HEADER = "Tasks with TITLE: %s";
    public static final String SEPARATOR = "-".repeat(14);
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String ALL_TITLES_ARGUMENT = "ALL_TITLES";
    private final TeamManagementRepository teamManagementRepository;

    public ListAllTasksCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<Task> tasks = teamManagementRepository.getTasks();
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        }

        String taskTitle = parameters.get(0);
        StringBuilder result = new StringBuilder();
        if (taskTitle.equals(ALL_TITLES_ARGUMENT)) {
            result.append(SEPARATOR).append(System.lineSeparator());
            result.append(String.format(TASKS_HEADER, taskTitle)).append(System.lineSeparator());
            result.append(SEPARATOR).append(System.lineSeparator());
            for (Task task : tasks) {
                result.append(task.print()).append(System.lineSeparator());
            }
            result.append(System.lineSeparator());
        } else {
            result.append(SEPARATOR).append(System.lineSeparator());
            result.append(String.format(TASKS_HEADER, taskTitle)).append(System.lineSeparator());
            result.append(SEPARATOR).append(System.lineSeparator());
            for (Task task : tasks) {
                if (task.getTitle().contains(taskTitle)) {
                    result.append(task.print()).append(System.lineSeparator());
                }
            }
            result.append(System.lineSeparator());
        }
        if (result.toString().trim().isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        } else {
            return result.toString().trim();
        }
    }
}
