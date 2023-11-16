package com.iliailievyuliankremenskiood.oop.taskmanagementTests.modelsTests;

import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
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

public class MemberImplTests {

    /*<-------Constant(s)------->*/
    public static final String VALID_NAME = "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN);
    public static final String SHORT_NAME = "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN-1);
    public static final String LONG_NAME = "A".repeat(MemberImpl.MEMBER_NAME_MAX_LEN+1);
    public static final int ID = 1;
    public static final String VALID_TITLE = "A".repeat(TaskImpl.MIN_TITLE_LENGTH);
    public static final String VALID_DESCRIPTION = "A".repeat(TaskImpl.MIN_DESCRIPTION_LENGTH);


    /*<-------Field(s)------->*/

    private MemberImpl member;

    /*Arrange*/
    @BeforeEach
    public void setMember() {
        member = new MemberImpl(VALID_NAME);
    }

    /*<-------Test(s)------->*/

    @Test
    public void constructor_Should_createMember_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(VALID_NAME,member.getName());
    }
    @Test
    public void constructor_Should_initializeTasks_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(0,member.getMemberTasks().size());
    }
    @Test
    public void constructor_Should_logActivityHistory_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(1,member.getActivityHistory().size());
    }
    @Test
    public void constructor_Should_ThrowError_When_ShorterNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new MemberImpl(SHORT_NAME)
        );
    }
    @Test
    public void constructor_Should_ThrowError_When_LongerNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new MemberImpl(LONG_NAME)
        );
    }
    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertNotSame(member.getMemberTasks(), member.getMemberTasks());
    }
    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertNotSame(member.getActivityHistory(), member.getActivityHistory());
    }
    @Test
    public void assignTask_Should_AddTaskToTheCollection() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        member.assignTask(task);
        /*Assert*/
        Assertions.assertEquals(1,member.getMemberTasks().size());
    }
    @Test
    public void assignTask_Should_AddHistoryLog() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        member.assignTask(task);
        /*Assert*/
        Assertions.assertEquals(2,member.getActivityHistory().size());

    }
    @Test
    public void unassignTask_Should_RemoveTaskToTheCollection() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        member.assignTask(task);
        member.unassignTask(task);
        /*Assert*/
        Assertions.assertEquals(0,member.getMemberTasks().size());
    }
    @Test
    public void unassignTask_Should_AddHistoryLog() {
        /*Arrange*/
        Member assignee = createValidMember();
        Task task = createValidTask(assignee);
        /*Act*/
        member.assignTask(task);
        member.unassignTask(task);
        /*Assert*/
        Assertions.assertEquals(3,member.getActivityHistory().size());

    }

    @Test
    public void viewTasksInfo_Should_NotThrowException_When_MethodCalled() {
        /*Arrange, Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> member.viewTasksInfo()
        );
    }
    @Test
    public void viewTasksInfo_Should_NotThrowException_When_MethodCalledAndThereAreTasks() {
        /*Arrange*/
        Member member = createValidMember();
        Task task = createValidTask(member);
        member.assignTask(task);
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> member.viewTasksInfo()
        );
    }
    @Test
    public void getActivityInfo_Should_NotThrowException_When_MethodCalled() {
        /*Arrange, Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> member.getActivityInfo()
        );
    }

    /*<-------Helper Method(s)------->*/

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
