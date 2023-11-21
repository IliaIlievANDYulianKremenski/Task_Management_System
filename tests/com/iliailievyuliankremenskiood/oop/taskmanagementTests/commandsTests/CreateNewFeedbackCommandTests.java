package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewFeedbackCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateNewFeedbackCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            CreateNewFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private CreateNewFeedbackCommand createNewFeedbackCommand;

    @BeforeEach
    public void setCreateNewFeedbackCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewFeedbackCommand = new CreateNewFeedbackCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createNewFeedbackCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_FeedbackRatingNotNumber() {
        List<String> list = List.of(
                "A".repeat(FeedbackImpl.MIN_TITLE_LENGTH),
                "A".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH),
                "Invalid rating",
                "NEW"
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> createNewFeedbackCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_StatusTypeNotValid() {
        List<String> list = List.of(
                "A".repeat(FeedbackImpl.MIN_TITLE_LENGTH),
                "A".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH),
                "1",
                "Invalid status"
        );
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createNewFeedbackCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_CreateNewFeedback_When_PassedValidInput() {
        List<String> list = List.of(
                "A".repeat(FeedbackImpl.MIN_TITLE_LENGTH),
                "A".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH),
                "1",
                "NEW"
        );
        createNewFeedbackCommand.execute(list);
        Assertions.assertEquals(
                1, teamManagementRepository.getFeedbacks().size()
        );
    }
}
