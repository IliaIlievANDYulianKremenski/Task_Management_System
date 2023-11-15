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

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ChangeFeedbackRatingCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private ChangeFeedbackRatingCommand changeFeedbackRatingCommand;

    /*Arrange*/
    @BeforeEach
    public void setChangeFeedbackRatingCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        changeFeedbackRatingCommand = new ChangeFeedbackRatingCommand(teamManagementRepository);
    }

    /*<-------Test(s)------->*/
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        /*Arrange*/
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> changeFeedbackRatingCommand.execute(list)
        );

    }

    @Test
    public void execute_Should_ThrowException_When_FeedbackIdNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "Feedback ID",
                "Rating"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> changeFeedbackRatingCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_FeedbackRatingNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                "Rating"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> changeFeedbackRatingCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ChangeFeedbackRating_When_PassedValidInput() {
        /*Arrange*/
        Feedback feedback = createValidFeedback();
        List<String> list = List.of(
                "1",
                "5"
        );
        /*Act*/
        changeFeedbackRatingCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                5,
                feedback.getRating()
        );

    }

    /*<-------Helper Method(s)------->*/

    private Feedback createValidFeedback() {
        return teamManagementRepository.createFeedback(
                "A".repeat(FeedbackImpl.MIN_TITLE_LENGTH),
                "A".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH),
                1,
                FeedbackStatusType.NEW
        );
    }
}
