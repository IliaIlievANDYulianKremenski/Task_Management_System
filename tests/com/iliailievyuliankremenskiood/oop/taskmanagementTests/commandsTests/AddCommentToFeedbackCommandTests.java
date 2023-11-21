package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddCommentToFeedbackCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.CommentImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;

import java.util.List;

public class AddCommentToFeedbackCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            AddCommentToFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private AddCommentToFeedbackCommand addCommentToFeedbackCommand;

    @BeforeEach
    public void setAddCommentToFeedbackCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        addCommentToFeedbackCommand = new AddCommentToFeedbackCommand(teamManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> addCommentToFeedbackCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_FeedbackIdNotNumber() {
        List<String> list = List.of(
                "Feedback ID",
                "A".repeat(CommentImpl.AUTHOR_MIN_LEN),
                "C".repeat(CommentImpl.MESSAGE_MIN_LEN)
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> addCommentToFeedbackCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_FeedbackDoNotExist() {
        List<String> list = List.of(
                "1",
                "A".repeat(CommentImpl.AUTHOR_MIN_LEN),
                "C".repeat(CommentImpl.MESSAGE_MIN_LEN)
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> addCommentToFeedbackCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_AddCommentToBug_When_PassedValidInput() {
        Feedback feedback = createValidFeedback();
        List<String> list = List.of(
                "1",
                "A".repeat(CommentImpl.AUTHOR_MIN_LEN),
                "C".repeat(CommentImpl.MESSAGE_MIN_LEN)
        );
        createValidMember();
        addCommentToFeedbackCommand.execute(list);
        Assertions.assertEquals(
                1,
                feedback.getComments().size()
        );
        Assertions.assertEquals(
                1,
                teamManagementRepository.getComments().size()
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

    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
    }
}
