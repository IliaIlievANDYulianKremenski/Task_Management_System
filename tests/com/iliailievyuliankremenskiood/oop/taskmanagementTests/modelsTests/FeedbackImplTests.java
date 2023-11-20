package com.iliailievyuliankremenskiood.oop.taskmanagementTests.modelsTests;

import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TaskImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackImplTests {

    /*<-------Constant(s)------->*/
    private static final String VALID_FEEDBACK_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_FEEDBACK_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final String SHORTER_FEEDBACK_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH - 1);
    private static final String LONGER_FEEDBACK_TITLE = "a".repeat(TaskImpl.MAX_TITLE_LENGTH + 1);

    private static final String SHORTER_FEEDBACK_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGTH - 1);
    private static final String LONGER_FEEDBACK_DESCRIPTION = "a".repeat(TaskImpl.MAX_DESCRIPTION_LENGTH + 1);
    private static final int VALID_FEEDBACK_RATING = 10;
    private static final FeedbackStatusType VALID_FEEDBACK_STATUS = FeedbackStatusType.NEW;
    private static final int VALID_TASK_ID = 1;

    private static final String VALID_FEEDBACK_PRINT = String.format("""
                    --------------
                    Feedback:
                        Id: %d
                    	Title: %s
                    	Description: %s
                        Rating: %d
                        Status: %s
                    --------------""", VALID_TASK_ID, VALID_FEEDBACK_TITLE, VALID_FEEDBACK_DESCRIPTION,
            VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS.toString());


    /*<-------Test(s)------->*/
    @Test
    public void constructor_Should_CreateValidFeedbackObject_When_ValidParamsPassed() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Assert*/
        Assertions.assertNotNull(feedback);
        Assertions.assertEquals(VALID_FEEDBACK_TITLE, feedback.getTitle());
    }

    @Test
    public void constructor_Should_ThrowException_When_ShorterTitlePassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, SHORTER_FEEDBACK_TITLE,
                            VALID_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void constructor_Should_ThrowException_When_LongerTitlePassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, LONGER_FEEDBACK_TITLE,
                            VALID_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void constructor_Should_ThrowException_When_ShorterDescriptionPassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                            SHORTER_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void constructor_Should_ThrowException_When_LongerDescriptionPassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                            LONGER_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void getId_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Assert*/
        Assertions.assertEquals(VALID_TASK_ID, feedback.getId());
    }

    @Test
    public void getTitle_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_TITLE, feedback.getTitle());
    }

    @Test
    public void getDescription_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_DESCRIPTION, feedback.getDescription());
    }

    @Test
    public void getRating_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_RATING, feedback.getRating());
    }

    @Test
    public void getStatus_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_STATUS, feedback.getStatus());
    }

/*    @Test
    public void print_should_ReturnTheCorrectPrint_When_ValidInstanceCreated() {
        *//*Arrange, Act*//*
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        *//*Assert*//*
        Assertions.assertEquals(VALID_FEEDBACK_PRINT, feedback.print());
    }*/

    @Test
    public void changeRating_Should_ChangeRating_When_NewRatingPassed() {
        /*Arrange*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Act*/
        feedback.changeRating(FeedbackImpl.FEEDBACK_MIN_RATING);

        /*Assert*/
        Assertions.assertEquals(FeedbackImpl.FEEDBACK_MIN_RATING, feedback.getRating());
        /* ✏️ NOTE ✏️:
         * 1 - I tried testing the exact content of the 2nd element of the ActivityHistory,
         * but it was more difficult than expected,
         * considering we are also logging the exact moment the log was created. */
    }

    @Test
    public void changeStatus_Should_ChangeStatus_When_NewStatusPassed() {
        /*Arrange*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Act*/
        feedback.changeStatus(FeedbackStatusType.SCHEDULED);

        /*Assert*/
        Assertions.assertEquals("SCHEDULED", feedback.getStatus().toString());
        /* ✏️ NOTE ✏️:
         * 1 - I tried testing the exact content of the 2nd element of the ActivityHistory,
         * but it was more difficult than expected,
         * considering we are also logging the exact moment the log was created. */
    }

    @Test
    public void getActivityHistory_Should_ReturnACopyOfTasksActivityHistory_When_ValidInstanceCreated() {
        /*Arrange*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Act, Assert*/
        Assertions.assertNotNull(feedback.getActivityHistory());
        Assertions.assertEquals(1, feedback.getActivityHistory().size());
        /* ✏️ NOTE ✏️:
         * 1 - I tried testing the exact content of the first element of the ActivityHistory,
         * but it was more difficult than expected,
         * considering we are also logging the exact moment the log was created. */
    }

}
