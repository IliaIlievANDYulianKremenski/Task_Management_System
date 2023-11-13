package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.oop.taskmanagement.utils.FormatterHelpers;
import com.iliailievyuliankremenskiood.oop.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardImpl implements Board {

    /*<-------Constant(s)------->*/
    private static final int BOARD_NAME_MIN_LEN = 5;
    private static final int BOARD_NAME_MAX_LEN = 10;
    private static final String BOARD_NAME_ERR_LEN = "Board name must be between %d and %d characters long!";

    private static final String BOARD_CREATE_MESSAGE = "[%s] Board %s has been crated.";
    public static final String TASK_CREATED_MESSAGE = "[%s] %s %s has been created in the board %s.";
    public static final String TASK_REMOVED_MESSAGE = "[%s] %s %s has been removed from the board %s.";


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
        activityHistory.add(String.format(
                TASK_CREATED_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                task.getClass().getSimpleName(),
                task.getTitle(),
                getName())
        );
    }
    public void removeTaskFromBoard(Task task) {
        tasks.remove(task);
        activityHistory.add(String.format(
                TASK_REMOVED_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                task.getClass().getSimpleName(),
                task.getTitle(),
                getName())
        );
    }
}