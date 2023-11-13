package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoryImplTests {

    /*<-------Constant(s)------->*/
    private static final String VALID_STORY_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_STORY_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGHT + 1);
    private static final String VALID_MEMBER_NAME = "Юлиан Кременски";
    private static final String VALID_MEMBER2_NAME = "Илия Илиев";
    private static final String SHORTER_STORY_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH - 1);
    private static final String LONGER_STORY_TITLE = "a".repeat(TaskImpl.MAX_TITLE_LENGTH + 1);

    private static final String SHORTER_STORY_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGHT - 1);
    private static final String LONGER_STORY_DESCRIPTION = "a".repeat(TaskImpl.MAX_DESCRIPTION_LENGHT + 1);

    private static final String VALID_STORY_PRINT = String.format("""
            --------------
            Story:
                Id: %d
            	Title: %s
            	Description: %s
                Priority: %s
                Size: %s
                Status: %s
                Assignee: %s
            --------------""", 1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH.toString(),
                StorySizeType.LARGE.toString(),StoryStatusType.IN_PROGRESS.toString(), VALID_MEMBER_NAME);

    @Test
    public void constructor_Should_CreateAValidStory_When_ValidArgumentsPassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS,member);

        Assertions.assertNotNull(story);
        Assertions.assertEquals(1, story.getId());
        Assertions.assertEquals(VALID_STORY_TITLE, story.getTitle());
        Assertions.assertEquals(VALID_STORY_DESCRIPTION, story.getDescription());
        Assertions.assertEquals(StoryPriorityType.HIGH.toString(), story.getPriority().toString());
        Assertions.assertEquals(StorySizeType.LARGE.toString(), story.getSize().toString());
        Assertions.assertEquals(VALID_MEMBER_NAME, story.getAssignee().getName());
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, SHORTER_STORY_TITLE, VALID_STORY_DESCRIPTION,
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
                    Bug bug = new BugImpl(1, LONGER_STORY_TITLE, VALID_STORY_DESCRIPTION,
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
                    Bug bug = new BugImpl(1, VALID_STORY_TITLE, SHORTER_STORY_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void Constructor_Should_ThrowError_When_LongerDescriptionPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, VALID_STORY_TITLE, LONGER_STORY_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void getStatus_Should_ReturnValidStatus() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(StoryStatusType.IN_PROGRESS.toString(), story.getStatus().toString());
    }

    @Test
    public void getPriority_Should_ReturnValidPriority() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(StoryPriorityType.HIGH.toString(), story.getPriority().toString());
    }

    @Test
    public void getSize_Should_ReturnValidSize() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(StorySizeType.LARGE.toString(), story.getSize().toString());
    }

    @Test
    public void getAssignee_Should_ReturnValidAssignee() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(VALID_MEMBER_NAME, story.getAssignee().getName());
    }

    @Test
    public void changeAssignee_Should_ChangeAssignee_When_ValidNewAssigneePassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Member member2 = new MemberImpl(VALID_MEMBER2_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changeAssignee(member2);
        /*Assess*/
        Assertions.assertEquals(VALID_MEMBER2_NAME, story.getAssignee().getName());
    }

    @Test
    public void changeSize_Should_ChangeSize_When_ValidNewSizePassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changeSize(StorySizeType.MEDIUM);
        /*Assess*/
        Assertions.assertEquals(StorySizeType.MEDIUM.toString(), story.getSize().toString());
    }

    @Test
    public void changeStatus_Should_ChangeStatus_When_ValidNewStatusPassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changeStatus(StoryStatusType.DONE);
        /*Assess*/
        Assertions.assertEquals(StoryStatusType.DONE.toString(), story.getStatus().toString());
    }

    @Test
    public void changePriority_Should_ChangePriority_When_ValidNewPriorityPassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changePriority(StoryPriorityType.LOW);
        /*Assess*/
        Assertions.assertEquals(StoryPriorityType.LOW.toString(), story.getPriority().toString());
    }

    @Test
    public void print_Should_CreateValidPrint(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        Assertions.assertEquals(VALID_STORY_PRINT, story.print());
    }

}
