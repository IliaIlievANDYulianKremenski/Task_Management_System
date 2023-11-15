package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddCommentToFeedbackCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;

import java.util.List;

public class AddCommentToFeedbackCommandTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            AddCommentToFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    TeamManagementRepository teamManagementRepository;
    private AddCommentToFeedbackCommand addCommentToFeedbackCommand;

    /*Arrange*/
    @BeforeEach
    public void setAddCommentToFeedbackCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        addCommentToFeedbackCommand = new AddCommentToFeedbackCommand(teamManagementRepository);
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
                () -> addCommentToFeedbackCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_FeedbackIdNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "Feedback ID",
                "Author",
                "Comment"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> addCommentToFeedbackCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_FeedbackDoNotExist() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                "Author",
                "Comment"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> addCommentToFeedbackCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_AddCommentToBug_When_PassedValidInput() {
        /*Arrange*/
        Feedback feedback = createValidFeedback();
        List<String> list = List.of(
                "1",
                "Author",
                "Comment"
        );
        /*Act*/
        addCommentToFeedbackCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                1,
                teamManagementRepository.getFeedbacks().get(0).getComments().size()
        );
        Assertions.assertEquals(
                1,
                teamManagementRepository.getComments().size()
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
