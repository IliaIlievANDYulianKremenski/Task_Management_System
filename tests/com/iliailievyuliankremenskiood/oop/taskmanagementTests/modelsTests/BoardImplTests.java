package com.iliailievyuliankremenskiood.oop.taskmanagementTests.modelsTests;

import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TaskImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTests {
    public static final String VALID_NAME = "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN);
    public static final String SHORTER_NAME = "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN - 1);
    public static final String LONGER_NAME = "A".repeat(BoardImpl.BOARD_NAME_MAX_LEN + 1);
    public static final int ID = 1;
    public static final String VALID_TITLE = "A".repeat(TaskImpl.MIN_TITLE_LENGTH);
    public static final String VALID_DESCRIPTION = "A".repeat(TaskImpl.MIN_DESCRIPTION_LENGTH);
    private BoardImpl board;

    @BeforeEach
    public void setBoard() {
        board = new BoardImpl(VALID_NAME);
    }

    @Test
    public void constructor_Should_createBoard_When_ValidArgumentsPassed() {
        Assertions.assertEquals(VALID_NAME, board.getName());
    }

    @Test
    public void constructor_Should_initializeTasks_When_ValidArgumentsPassed() {
        Assertions.assertEquals(0, board.getBoardTasks().size());
    }

    @Test
    public void constructor_Should_logActivityHistory_When_ValidArgumentsPassed() {
        Assertions.assertEquals(1, board.getActivityHistory().size());
    }

    @Test
    public void Constructor_Should_ThrowError_When_ShorterNamePassed() {
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new BoardImpl(SHORTER_NAME)
        );
    }

    @Test
    public void Constructor_Should_ThrowError_When_LongerNamePassed() {
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new BoardImpl(LONGER_NAME)
        );
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(board.getBoardTasks(), board.getBoardTasks());
    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(board.getActivityHistory(), board.getActivityHistory());
    }

    @Test
    public void createTaskInBoard_Should_AddTaskToTheCollection() {
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        board.createTaskInBoard(task);
        Assertions.assertEquals(1, board.getBoardTasks().size());
    }

    @Test
    public void createTaskInBoard_Should_AddHistoryLog() {
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        board.createTaskInBoard(task);
        Assertions.assertEquals(2, board.getActivityHistory().size());
    }

    @Test
    public void removeTaskInBoard_Should_RemoveTaskToTheCollection() {
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        board.createTaskInBoard(task);
        board.removeTaskFromBoard(task);
        Assertions.assertEquals(0, board.getBoardTasks().size());
    }

    @Test
    public void removeTaskInBoard_Should_AddHistoryLog() {
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        board.createTaskInBoard(task);
        board.removeTaskFromBoard(task);
        Assertions.assertEquals(3, board.getActivityHistory().size());
    }

    @Test
    public void getActivityInfo_Should_NotThrowException_When_MethodCalled() {
        Assertions.assertDoesNotThrow(
                () -> board.getActivityInfo()
        );
    }

    private static Member createValidMember() {
        return new MemberImpl(MemberImplTests.VALID_NAME);
    }

    private static Task createValidTask(Member assignee) {
        return new BugImpl(
                ID,
                VALID_TITLE,
                VALID_DESCRIPTION,
                BugPriorityType.HIGH,
                BugSeverityType.CRITICAL,
                assignee);
    }
}
