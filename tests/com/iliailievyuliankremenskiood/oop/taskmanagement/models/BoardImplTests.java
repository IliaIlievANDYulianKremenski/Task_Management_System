package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTests {

    private BoardImpl board;
    @BeforeEach
    public void setBoard() {
        board = new BoardImpl("Board");
    }

    @Test
    public void constructor_Should_createBoard_When_ValidArgumentsPassed() {

    }
    @Test
    public void constructor_Should_initializeTasks_When_ValidArgumentsPassed() {

    }
    @Test
    public void constructor_Should_logActivityHistory_When_ValidArgumentsPassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterNamePassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerNamePassed() {

    }
    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {

    }
    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {

    }
    @Test
    public void createTaskInBoard_Should_AddTaskToTheCollection() {

    }
    @Test
    public void createTaskInBoard_Should_AddHistoryLog() {

    }
    @Test
    public void removeTaskInBoard_Should_RemoveTaskToTheCollection() {

    }
    @Test
    public void removeTaskInBoard_Should_AddHistoryLog() {

    }
}
