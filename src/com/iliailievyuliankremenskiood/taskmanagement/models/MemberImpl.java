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

    /*<-------Constant(s)------->*/

    public static final int MEMBER_NAME_MIN_LEN = 5;
    public static final int MEMBER_NAME_MAX_LEN = 15;
    private static final String MEMBER_NAME_ERR_LEN = "Member name must be between %d and %d characters long!";
    private static final String TASK_ASSIGN_MESSAGE = "[%s] The %s task has been assigned to %s.";
    private static final String TASK_UNASSIGNED_MESSAGE = "[%s] %s's task %s has been unassigned.";
    private static final String PERSON_CREATE_MESSAGE = "[%s] Person %s has been crated.";
    private static final String ACTIVITY_HISTORY_HEADER = "--- %s Activity History ---";
    private static final String NO_TASKS_MESSAGE = "No tasks are assigned to %s.";
    private static final String TASK_INFO_HEADER = "--- %s Tasks ---";
    private static final String SEPARATOR = "--------------";


    /*<-------Field(s)------->*/

    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    /*<-------Constructor(s)------->*/

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

    /*<-------Getter(s)------->*/

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    @Override
    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    /*<-------Setter(s)------->*/

    private void setName(String name) {
        ValidationHelpers.validateStringLength(
                name,
                MEMBER_NAME_MIN_LEN,
                MEMBER_NAME_MAX_LEN,
                String.format(MEMBER_NAME_ERR_LEN,MEMBER_NAME_MIN_LEN,MEMBER_NAME_MAX_LEN)
        );
        this.name = name;
    }

    /*<-------Behavioural Method(s)------->*/

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

    public void viewTasksInfo() {
        StringBuilder taskInfo = new StringBuilder();
        if (tasks.isEmpty()) {
            taskInfo.append(String.format(
                    NO_TASKS_MESSAGE,
                    getName())
            );

        } else {
            taskInfo.append(String.format(
                    TASK_INFO_HEADER,
                    getName())
            );
            taskInfo.append(System.lineSeparator());
            taskInfo.append(SEPARATOR).append(System.lineSeparator());
            sortTasks();
            for (int i = 0; i < getTasks().size(); i++) {
                taskInfo.append(getTasks().get(i).getTitle());
                taskInfo.append(System.lineSeparator());
            }
            taskInfo.append(SEPARATOR).append(System.lineSeparator());
        }
        System.out.println(taskInfo.toString().trim());
    }

    public void viewActivityInfo() {
        StringBuilder activityInfo = new StringBuilder();
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
        System.out.println(activityInfo.toString().trim());
    }


    /*<-------Helper Method(s)------->*/
    @Override
    public void logCreation(String creationString) {
        this.activityHistory.add(creationString);
    }

    private void sortTasks() {
        tasks.sort(Comparator.comparing(Task::getTitle));
    }
}
