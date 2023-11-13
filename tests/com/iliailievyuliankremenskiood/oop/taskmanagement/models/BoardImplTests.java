package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTests {
    
    /*<-------Constant(s)------->*/

    public static final String VALID_NAME = "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN);
    public static final String SHORTER_NAME = "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN-1);
    public static final String LONGER_NAME = "A".repeat(BoardImpl.BOARD_NAME_MAX_LEN+1);
    
    /*<-------Field(s)------->*/

    private BoardImpl board;
    
    /*Arrange*/
    @BeforeEach
    public void setBoard() {
        board = new BoardImpl(VALID_NAME);
    }

    /*<-------Test(s)------->*/

    @Test
    public void constructor_Should_createBoard_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(VALID_NAME,board.getName());
    }
    @Test
    public void constructor_Should_initializeTasks_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(0,board.getTasks().size());
    }
    @Test
    public void constructor_Should_logActivityHistory_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(1,board.getActivityHistory().size());
    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new BoardImpl(SHORTER_NAME)
                );
    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new BoardImpl(LONGER_NAME)
        );
    }
    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertFalse(board.getTasks() == board.getTasks());
    }
    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertFalse(board.getActivityHistory() == board.getActivityHistory());
    }
    @Test
    public void createTaskInBoard_Should_AddTaskToTheCollection() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        board.createTaskInBoard(task);
        /*Assert*/
        Assertions.assertEquals(1,board.getTasks().size());
    }

    @Test
    public void createTaskInBoard_Should_AddHistoryLog() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        board.createTaskInBoard(task);
        /*Assert*/
        Assertions.assertEquals(2,board.getActivityHistory().size());
    }
    @Test
    public void removeTaskInBoard_Should_RemoveTaskToTheCollection() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        board.createTaskInBoard(task);
        board.removeTaskFromBoard(task);
        /*Assert*/
        Assertions.assertEquals(0,board.getTasks().size());
    }
    @Test
    public void removeTaskInBoard_Should_AddHistoryLog() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        board.createTaskInBoard(task);
        board.removeTaskFromBoard(task);
        /*Assert*/
        Assertions.assertEquals(3,board.getActivityHistory().size());
    }
    
    
    /*<-------Helper Method(s)------->*/

    private static Member createValidMember() {
        return new MemberImpl(MemberImplTests.VALID_NAME);
    }
    private static Task createValidTask(Member assignee) {
        return new BugImpl(1,"This is a valid Title","This is a valid Description", BugPriorityType.HIGH, BugSeverityType.CRITICAL, assignee);
    }
}
