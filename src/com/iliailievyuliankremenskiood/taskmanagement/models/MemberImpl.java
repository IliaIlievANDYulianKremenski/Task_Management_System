package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FormatterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MemberImpl implements Member {

    public static final int MEMBER_NAME_MIN_LEN = 5;
    public static final int MEMBER_NAME_MAX_LEN = 15;
    private static final String MEMBER_NAME_ERR_LEN = "Member name must be between %d and %d characters long!";
    private static final String TASK_ASSIGN_MESSAGE = "[%s] The %s task has been assigned to %s.";
    private static final String TASK_UNASSIGNED_MESSAGE = "[%s] %s's task %s has been unassigned.";
    private static final String PERSON_CREATE_MESSAGE = "[%s] Person '%s' has been created.";
    private static final String ACTIVITY_HISTORY_HEADER = "%s activity history: ";
    private static final String NO_TASKS_MESSAGE = "No tasks are assigned to %s.";
    private static final String TASK_INFO_HEADER = "%s tasks: ";
    private static final String SEPARATOR = "--------------";
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>(
                Arrays.asList(String.format(
                        PERSON_CREATE_MESSAGE,
                        LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                        name))
        );
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getMemberTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(
                name,
                MEMBER_NAME_MIN_LEN,
                MEMBER_NAME_MAX_LEN,
                String.format(MEMBER_NAME_ERR_LEN, MEMBER_NAME_MIN_LEN, MEMBER_NAME_MAX_LEN)
        );
        this.name = name;
    }

    public void assignTask(Task task) {
        tasks.add(task);
        logCreation(String.format(
                TASK_ASSIGN_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                task.getTitle(),
                getName())
        );
    }

    public void unassignTask(Task task) {
        tasks.remove(task);
        logCreation(String.format(
                TASK_UNASSIGNED_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                getName(),
                task.getTitle())
        );
    }

    public String viewTasksInfo() {
        StringBuilder taskInfo = new StringBuilder();
        if (tasks.isEmpty()) {
            taskInfo.append(String.format(
                    NO_TASKS_MESSAGE,
                    getName())
            );

        } else {
            taskInfo.append(SEPARATOR).append(System.lineSeparator());
            taskInfo.append(String.format(
                    TASK_INFO_HEADER,
                    getName())
            );
            taskInfo.append(System.lineSeparator());
            taskInfo.append(SEPARATOR).append(System.lineSeparator());
            sortTasks();
            for (int i = 0; i < getMemberTasks().size(); i++) {
                taskInfo.append(getMemberTasks().get(i).getTitle());
                taskInfo.append(System.lineSeparator());
            }
            taskInfo.append(SEPARATOR).append(System.lineSeparator());
        }
        return taskInfo.toString().trim();
    }

    public String getActivityInfo() {
        StringBuilder activityInfo = new StringBuilder();
        activityInfo.append(SEPARATOR).append(System.lineSeparator());
        activityInfo.append(String.format(
                ACTIVITY_HISTORY_HEADER,
                getName())
        );
        activityInfo.append(System.lineSeparator());
        activityInfo.append(SEPARATOR).append(System.lineSeparator());

        for (int i = 0; i < getActivityHistory().size(); i++) {
            activityInfo.append(getActivityHistory().get(i));
            activityInfo.append(System.lineSeparator());
        }
        activityInfo.append(SEPARATOR).append(System.lineSeparator());
        return activityInfo.toString().trim();
    }

    @Override
    public void logCreation(String creationString) {
        this.activityHistory.add(creationString);
    }

    private void sortTasks() {
        tasks.sort(Comparator.comparing(Task::getTitle));
    }
}
