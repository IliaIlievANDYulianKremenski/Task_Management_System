package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListTasksWithAssigneeCommand implements Command {
    /**
     * Command format: List_Tasks_With_Assignee {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ALL_STATUSES_ARGUMENT = "ALL_STATUSES";
    public static final String ALL_ASSIGNEES_ARGUMENT = "ALL_ASSIGNEES";
    public static final String NO_TASKS_ERROR = "There are no Tasks with assignees (Bugs or Stories) with STATUS: %s and ASSIGNEE: %s";
    public static final String TASKS_HEADER = "Tasks with STATUS: %s and ASSIGNEE: %s";
    public static final String SEPARATOR = "-".repeat(14);
    private final TeamManagementRepository teamManagementRepository;

    public ListTasksWithAssigneeCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<Bug> bugs = teamManagementRepository.getBugs();
        bugs.sort(Comparator.comparing(Bug::getTitle));
        List<Story> stories = teamManagementRepository.getStories();
        stories.sort(Comparator.comparing(Story::getTitle));
        String taskStatus = parameters.get(0);
        String taskAssignee = parameters.get(1);
        if (bugs.isEmpty()
                && stories.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_TASKS_ERROR, taskStatus, taskAssignee));
        }
        StringBuilder result = new StringBuilder();
        result.append(String.format(TASKS_HEADER, taskStatus, taskAssignee)).append(System.lineSeparator());
        if (taskStatus.equalsIgnoreCase(ALL_STATUSES_ARGUMENT)
                && taskAssignee.equalsIgnoreCase(ALL_ASSIGNEES_ARGUMENT)) {
            displayAllTasksWithoutFiltering(bugs, stories, result);
        } else if (taskStatus.equalsIgnoreCase(ALL_STATUSES_ARGUMENT)
                && !taskAssignee.equalsIgnoreCase(ALL_ASSIGNEES_ARGUMENT)) {
            displayAllTasksWithSpecificAssignee(bugs, stories, result, taskAssignee);
        } else if (!taskStatus.equalsIgnoreCase(ALL_STATUSES_ARGUMENT)
                && taskAssignee.equalsIgnoreCase(ALL_ASSIGNEES_ARGUMENT)) {
            displayAllTasksWithSpecificStatus(bugs, stories, result, taskStatus);
        } else if (!taskStatus.equalsIgnoreCase(ALL_STATUSES_ARGUMENT)
                && !taskAssignee.equalsIgnoreCase(ALL_ASSIGNEES_ARGUMENT)) {
            displayAllTasksWithSpecificStatusAndAssignee(bugs, stories, result, taskStatus, taskAssignee);
        }
        return result.toString().trim();
    }

    private void displayAllTasksWithoutFiltering(List<Bug> bugs, List<Story> stories, StringBuilder result) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());

        for (Bug bug : bugs) {
            result.append(bug.print()).append(System.lineSeparator());
        }
        for (Story story : stories) {
            result.append(story.print()).append(System.lineSeparator());
        }
        result.append(System.lineSeparator());
    }

    private void displayAllTasksWithSpecificAssignee(List<Bug> bugs, List<Story> stories, StringBuilder result, String taskAssignee) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : bugs) {
            if (bug.getAssignee() != null
                    && bug.getAssignee().getName().contains(taskAssignee)) {
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        for (Story story : stories) {
            if (story.getAssignee() != null
                    && story.getAssignee().getName().contains(taskAssignee)) {
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }

    private void displayAllTasksWithSpecificStatus(List<Bug> bugs, List<Story> stories, StringBuilder result, String taskStatus) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : bugs) {
            if (bug.getStatus().toString().contains(taskStatus.toUpperCase())) {
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        for (Story story : stories) {
            if (story.getStatus().toString().contains(taskStatus.toUpperCase())) {
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }

    private void displayAllTasksWithSpecificStatusAndAssignee(
            List<Bug> bugs, List<Story> stories, StringBuilder result, String taskStatus, String taskAssignee) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : bugs) {
            if (bug.getStatus().toString().contains(taskStatus.toUpperCase())
                    && bug.getAssignee() != null
                    && bug.getAssignee().getName().contains(taskAssignee)) {
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        for (Story story : stories) {
            if (story.getStatus().toString().contains(taskStatus.toUpperCase())
                    && story.getAssignee() != null
                    && story.getAssignee().getName().contains(taskAssignee)) {
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }
}
