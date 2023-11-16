package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ListTasksWithAssigneeCommand implements Command {
    /**
     * Command format: List_All_Tasks_With_Assignee {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
     */

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ALL_STATUSES_ARGUMENT = "ALL_STATUSES";
    public static final String ALL_ASSIGNEES_ARGUMENT = "ALL_ASSIGNEES";
    public static final String NO_TASKS_ERROR = "There are no Tasks with assignees (Bugs or Stories) to be listed.";
    public static final String TASKS_HEADER = "Tasks: ";
    public static final String SEPARATOR = "-".repeat(14);

    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ListTasksWithAssigneeCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);


        if (teamManagementRepository.getBugs().isEmpty()
                && teamManagementRepository.getStories().isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        }

        String taskStatus = parameters.get(0);
        String taskAssignee = parameters.get(1);
        StringBuilder result = new StringBuilder();

        /*User supplies ALL_STATUSES + ALL_ASSIGNEES as command arguments*/
        if (taskStatus.equalsIgnoreCase(ALL_STATUSES_ARGUMENT)
                && taskAssignee.equalsIgnoreCase(ALL_ASSIGNEES_ARGUMENT)) {
            displayAllTasksWithoutFiltering(result);
        }
        /*User supplies ALL_STATUSES + some name to filter the assignees by*/
        else if (taskStatus.equalsIgnoreCase(ALL_STATUSES_ARGUMENT)
                && !taskAssignee.equals(ALL_ASSIGNEES_ARGUMENT)) {
            displayTasksFilteredByAssignee(result, taskAssignee);
        }
        /*The user supplies some status + ALL_ASSIGNEES as command arguments*/
        else if (!taskStatus.equals(ALL_STATUSES_ARGUMENT)
                && taskAssignee.equals(ALL_ASSIGNEES_ARGUMENT)) {
            /*Status == DONE. Both Story and Bug have a status "DONE"*/
            if (taskStatus.equalsIgnoreCase("DONE")) {
                displayTasksFilteredByStatusDone(result);
            }
            /*Status == NOT_DONE or IN_PROGRESS - only the stories have such a status*/
            else if (taskStatus.equalsIgnoreCase("NOT_DONE")
                    || taskStatus.equalsIgnoreCase("IN_PROGRESS")) {
                displayStoriesFilteredByStatus(result);
            }
            /*Status == ACTIVE - only bugs have this status*/
            else if (taskStatus.equalsIgnoreCase("ACTIVE")) {
                displayBugsFilteredByStatus(result);
            }
        }
        /*The user supplies filter word for both the status and the assignee*/
        else if(!taskStatus.equals(ALL_STATUSES_ARGUMENT)
                && !taskAssignee.equals(ALL_ASSIGNEES_ARGUMENT)){
            displayAllTasksFilteredByStatusAndAssignee(result, taskStatus, taskAssignee);
        }

        return result.toString().trim();
    }

    private void displayAllTasksWithoutFiltering(StringBuilder result) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());

        for (Bug bug : teamManagementRepository.getBugs()) {
            result.append(bug.print()).append(System.lineSeparator());
        }
        for (Story story : teamManagementRepository.getStories()) {
            result.append(story.print()).append(System.lineSeparator());
        }
        result.append(System.lineSeparator());
    }

    private void displayTasksFilteredByAssignee(StringBuilder result, String taskAssignee) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : teamManagementRepository.getBugs()) {
            if (bug.getAssignee().getName().equals(taskAssignee)) {
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        for (Story story : teamManagementRepository.getStories()) {
            if (story.getAssignee().getName().equals(taskAssignee)) {
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }

    private void displayTasksFilteredByStatusDone(StringBuilder result) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : teamManagementRepository.getBugs()) {
            if (bug.getStatus().toString().equalsIgnoreCase("DONE")) {
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        for (Story story : teamManagementRepository.getStories()) {
            if (story.getStatus().toString().equalsIgnoreCase("DONE")) {
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }

    private void displayStoriesFilteredByStatus(StringBuilder result) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Story story : teamManagementRepository.getStories()) {
            if (story.getStatus().toString().equalsIgnoreCase("NOT_DONE")
                    || story.getStatus().toString().equalsIgnoreCase("IN_PROGRESS")) {
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }

    private void displayBugsFilteredByStatus(StringBuilder result) {
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : teamManagementRepository.getBugs()) {
            if (bug.getStatus().toString().equalsIgnoreCase("ACTIVE")) {
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }

    private void displayAllTasksFilteredByStatusAndAssignee(StringBuilder result, String status, String assignee){
        result.append(SEPARATOR).append(System.lineSeparator());
        result.append(TASKS_HEADER).append(System.lineSeparator());
        result.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : teamManagementRepository.getBugs()) {
            if(bug.getStatus().toString().equalsIgnoreCase(status)
                    && bug.getAssignee().getName().equalsIgnoreCase(assignee)){
                result.append(bug.print()).append(System.lineSeparator());
            }
        }
        for (Story story : teamManagementRepository.getStories()) {
            if(story.getStatus().toString().equalsIgnoreCase(status)
                    && story.getAssignee().getName().equalsIgnoreCase(assignee)){
                result.append(story.print()).append(System.lineSeparator());
            }
        }
        result.append(System.lineSeparator());
    }
}
