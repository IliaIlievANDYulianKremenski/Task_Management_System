package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FormatterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardImpl implements Board {

    /*<-------Constant(s)------->*/
    public static final int BOARD_NAME_MIN_LEN = 5;
    public static final int BOARD_NAME_MAX_LEN = 10;
    private static final String BOARD_NAME_ERR_LEN = "Board name must be between %d and %d characters long!";
    private static final String BOARD_CREATE_MESSAGE = "[%s] Board %s has been created.";
    private static final String TASK_CREATED_MESSAGE = "[%s] %s %s has been created in the board %s.";
    private static final String TASK_REMOVED_MESSAGE = "[%s] %s %s has been removed from the board %s.";
    private static final String ACTIVITY_HISTORY_HEADER = "%s activity history: ";
    private static final String SEPARATOR = "--------------";

    /*<-------Field(s)------->*/
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    /*<-------Constructor(s)------->*/

    public BoardImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>(
                Arrays.asList(String.format(
                        BOARD_CREATE_MESSAGE,
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
    public List<Task> getBoardTasks() {
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
                BOARD_NAME_MIN_LEN,
                BOARD_NAME_MAX_LEN,
                String.format(BOARD_NAME_ERR_LEN,BOARD_NAME_MIN_LEN,BOARD_NAME_MAX_LEN)
        );
        this.name = name;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public void createTaskInBoard(Task task) {
        tasks.add(task);
        logCreation(String.format(
                TASK_CREATED_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                task.getClass().getSimpleName(),
                task.getTitle(),
                getName())
        );
    }
    public void removeTaskFromBoard(Task task) {
        tasks.remove(task);
        logCreation(String.format(
                TASK_REMOVED_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                task.getClass().getSimpleName(),
                task.getTitle(),
                getName())
        );
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

    /*<-------Helper Method(s)------->*/
    @Override
    public void logCreation(String creationString) {
        this.activityHistory.add(creationString);
    }
}