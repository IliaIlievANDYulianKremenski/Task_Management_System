package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Task;
import utils.FormatterHelpers;
import utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MemberImpl implements Member {

    /*<-------Constant(s)------->*/
    public static final int MEMBER_NAME_MIN_LEN = 5;
    public static final int MEMBER_NAME_MAX_LEN = 15;
    public static final String MEMBER_NAME_ERR_LEN = "Content must be between %d and %d characters long!";
    public static final String TASK_ASSIGN_MESSAGE = "[%s] The %s task has been assigned to %s.";
    public static final String TASK_UNASSIGNED_MESSAGE = "[%s] %s's task %s has been unassigned.";
    public static final String PERSON_CREATE_MESSAGE = "[%s] Person %s has been crated.";
    public static final String ACTIVITY_HISTORY_HEADER = "--- %s Activity History ---";
    public static final String NO_TASKS_MESSAGE = "No tasks are assigned to %s.";
    public static final String TASK_INFO_HEADER = "--- %s Tasks ---";
    public static final String SEPARATOR = "--------------\n";


    /*<-------Field(s)------->*/
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>(
                Arrays.asList(String.format(
                        LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                        PERSON_CREATE_MESSAGE,
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

    //TODO
    /* Name must be unique in the whole system. Have to think how to implement it.
    * Probably this will be done in the repository.*/

    public void setName(String name) {
        ValidationHelpers.validateStringLength(
                name,
                MEMBER_NAME_MIN_LEN,
                MEMBER_NAME_MAX_LEN,
                MEMBER_NAME_ERR_LEN
        );
        this.name = name;
    }

    /*<-------Behavioural Method(s)------->*/

    public void assignTask(Task task) {
        tasks.add(task);
        activityHistory.add(String.format(
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                TASK_ASSIGN_MESSAGE,
                task.getTitle(),
                getName())
        );
    }
    public void unassignTask(Task task) {
        tasks.remove(task);
        activityHistory.add(String.format(
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                TASK_UNASSIGNED_MESSAGE,
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
            taskInfo.append(SEPARATOR);
            sortTasks();
            for (int i = 0; i < getTasks().size(); i++) {
                taskInfo.append(getTasks().get(i));
                if (i != getTasks().size()-1) {
                    taskInfo.append(System.lineSeparator());
                }
            }
            taskInfo.append(SEPARATOR);
        }
        System.out.println(taskInfo.toString().trim());
    }

    public void viewActivityInfo() {
        StringBuilder activityInfo = new StringBuilder();
        activityInfo.append(String.format(
                ACTIVITY_HISTORY_HEADER,
                getName())
        );
        activityInfo.append(SEPARATOR);

        for (int i = 0; i < getActivityHistory().size(); i++) {
            activityInfo.append(getActivityHistory().get(i));
            if (i != getActivityHistory().size()-1) {
                activityInfo.append(System.lineSeparator());
            }
        }
        activityInfo.append(SEPARATOR);
        System.out.println(activityInfo.toString().trim());
    }


    /*<-------Helper Method(s)------->*/
    private void sortTasks() {
        tasks.sort(Comparator.comparing(Task::getTitle));
    }
}
