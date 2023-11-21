package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddCommentToFeedbackCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ChangeFeedbackRatingCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangeFeedbackRatingCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ChangeFeedbackRatingCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ChangeFeedbackRatingCommand changeFeedbackRatingCommand;

    @BeforeEach
    public void setChangeFeedbackRatingCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        changeFeedbackRatingCommand = new ChangeFeedbackRatingCommand(teamManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> changeFeedbackRatingCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_FeedbackIdNotNumber() {
        List<String> list = List.of(
                "Feedback ID",
                "Rating"
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> changeFeedbackRatingCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_FeedbackRatingNotNumber() {
        List<String> list = List.of(
                "1",
                "Rating"
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> changeFeedbackRatingCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ChangeFeedbackRating_When_PassedValidInput() {
        Feedback feedback = createValidFeedback();
        List<String> list = List.of(
                "1",
                "5"
        );
        changeFeedbackRatingCommand.execute(list);
        Assertions.assertEquals(
                5,
                feedback.getRating()
        );
    }

    private Feedback createValidFeedback() {
        return teamManagementRepository.createFeedback(
                "A".repeat(FeedbackImpl.MIN_TITLE_LENGTH),
                "A".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH),
                1,
                FeedbackStatusType.NEW
        );
    }
}
