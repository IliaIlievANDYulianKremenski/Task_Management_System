package com.iliailievyuliankremenskiood.oop.taskmanagementTests.modelsTests;

import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TaskImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackImplTests {
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

    @Test
    public void constructor_Should_CreateValidFeedbackObject_When_ValidParamsPassed() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertNotNull(feedback);
        Assertions.assertEquals(VALID_FEEDBACK_TITLE, feedback.getTitle());
    }

    @Test
    public void constructor_Should_ThrowException_When_ShorterTitlePassed() {
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
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertEquals(VALID_TASK_ID, feedback.getId());
    }

    @Test
    public void getTitle_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertEquals(VALID_FEEDBACK_TITLE, feedback.getTitle());
    }

    @Test
    public void getDescription_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertEquals(VALID_FEEDBACK_DESCRIPTION, feedback.getDescription());
    }

    @Test
    public void getRating_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertEquals(VALID_FEEDBACK_RATING, feedback.getRating());
    }

    @Test
    public void getStatus_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertEquals(VALID_FEEDBACK_STATUS, feedback.getStatus());
    }

    @Test
    public void changeRating_Should_ChangeRating_When_NewRatingPassed() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        feedback.changeRating(FeedbackImpl.FEEDBACK_MIN_RATING);
        Assertions.assertEquals(FeedbackImpl.FEEDBACK_MIN_RATING, feedback.getRating());
    }

    @Test
    public void changeStatus_Should_ChangeStatus_When_NewStatusPassed() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        feedback.changeStatus(FeedbackStatusType.SCHEDULED);
        Assertions.assertEquals("SCHEDULED", feedback.getStatus().toString());
    }

    @Test
    public void getActivityHistory_Should_ReturnACopyOfTasksActivityHistory_When_ValidInstanceCreated() {
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        Assertions.assertNotNull(feedback.getActivityHistory());
        Assertions.assertEquals(1, feedback.getActivityHistory().size());
    }
}
