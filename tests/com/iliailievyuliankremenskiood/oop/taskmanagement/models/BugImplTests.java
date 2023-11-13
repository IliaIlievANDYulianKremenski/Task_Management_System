package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BugImplTests {

    /*<-------Constant(s)------->*/
    private static final String VALID_BUG_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_BUG_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGHT + 1);
    private static final String VALID_MEMBER_NAME = "Юлиан Кременски";
    private static final String VALID_MEMBER2_NAME = "Илия Илиев";
    private static final String SHORTER_BUG_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH - 1);
    private static final String LONGER_BUG_TITLE = "a".repeat(TaskImpl.MAX_TITLE_LENGTH + 1);
    private static final String SHORTER_BUG_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGHT - 1);
    private static final String LONGER_BUG_DESCRIPTION = "a".repeat(TaskImpl.MAX_DESCRIPTION_LENGHT + 1);
    private static final String VALID_BUG_PRINT = String.format("""
                    --------------
                    Bug:
                        Id: %d
                    	Title: %s
                    	Description: %s
                        Priority: %s
                        Severity: %s
                        Status: %s
                        Assignee: %s
                    --------------""", 1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION, BugPriorityType.HIGH.toString(),
            BugSeverityType.CRITICAL, BugStatusType.ACTIVE.toString(), VALID_MEMBER_NAME);
    public static final String STEP_1 = "Step 1.";
    public static final String STEP_2 = "Step 2.";

    /*TODO - A valid implementation of the "Member" class is needed for the tests. @Ilia Iliev will implement it 👍*/
    /*<-------Test(s)------->*/
    @Test
    public void Constructor_Should_CreateNewBug_When_ValidArgumentsPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);

        /*Act*/
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Assess*/
        Assertions.assertNotNull(bug);
        Assertions.assertEquals(1, bug.getId());
        Assertions.assertEquals(VALID_BUG_TITLE, bug.getTitle());
        Assertions.assertEquals(VALID_BUG_DESCRIPTION, bug.getDescription());
        Assertions.assertEquals(BugPriorityType.HIGH.toString(), bug.getPriority().toString());
        Assertions.assertEquals(BugSeverityType.CRITICAL.toString(), bug.getSeverity().toString());
        Assertions.assertEquals(VALID_MEMBER_NAME, bug.getAssignee().getName());
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, SHORTER_BUG_TITLE, VALID_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void constructor_Should_ThrowError_When_LongerTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, LONGER_BUG_TITLE, VALID_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterDescriptionPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, VALID_BUG_TITLE, SHORTER_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void constructor_Should_ThrowError_When_LongerDescriptionPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, VALID_BUG_TITLE, LONGER_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void getStepsToReproduce_Should_ReturnACopyOfTheOriginalList() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act, Assert*/
        Assertions.assertEquals(0, bug.getStepsToReproduce().size());
    }

    @Test
    public void print_Should_PrintCorrectMessage() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act, Assert*/
        Assertions.assertEquals(VALID_BUG_PRINT, bug.print());
    }

    @Test
    public void addStepsToReproduce_Should_AddListOfStepsToTheBug_When_ValidStepsPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
        List<String> stepsToReproduce = new ArrayList<>();
        stepsToReproduce.add(STEP_1);
        stepsToReproduce.add(STEP_2);

        /*Act*/
        bug.addStepsToReproduce(stepsToReproduce);

        /*Assert*/
        Assertions.assertEquals(2, bug.getStepsToReproduce().size());
        Assertions.assertEquals(STEP_1, bug.getStepsToReproduce().get(0));
        Assertions.assertEquals(STEP_2, bug.getStepsToReproduce().get(1));

    }


    @Test
    public void changeBugStatus_Should_ChangeStatus() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeBugStatus();

        /*Assert*/
        Assertions.assertEquals(BugStatusType.DONE.toString(), bug.getStatus().toString());
    }

    @Test
    public void changeBugPriority_Should_ChangePriority() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeBugPriority(BugPriorityType.MEDIUM);

        /*Assert*/
        Assertions.assertEquals(BugPriorityType.MEDIUM.toString(), bug.getPriority().toString());
    }

    @Test
    public void changeBugSeverity_Should_ChangeSeverity() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeBugSeverity(BugSeverityType.MINOR);

        /*Assert*/
        Assertions.assertEquals(BugSeverityType.MINOR.toString(), bug.getSeverity().toString());
    }

    @Test
    public void changeAssignee_Should_ChangeAssignee_When_ValidAssigneePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Member member2 = new MemberImpl(VALID_MEMBER2_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeAssignee(member2);

        /*Assert*/
        Assertions.assertEquals(VALID_MEMBER2_NAME, bug.getAssignee().getName());
    }

    @Test
    public void addCommentToTask_Should_AddACommentToTheListOfComments(){
        Comment comment = new CommentImpl(VALID_MEMBER_NAME, VALID_BUG_DESCRIPTION);
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Task bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        bug.addCommentToTask(comment);

        Assertions.assertEquals(VALID_BUG_DESCRIPTION, bug.getComments().get(0).getMessage());

    }

}
