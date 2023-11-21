package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllFeedbacksCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ListAllFeedbacksCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllFeedbacksCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ListAllFeedbacksCommand listAllFeedbacksCommand;

    @BeforeEach
    public void setListAllFeedbacksCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listAllFeedbacksCommand = new ListAllFeedbacksCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllFeedbacksCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoFeedbacksToList() {
        List<String> list = List.of(
                "ALL_STATUSES"
        );
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllFeedbacksCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoFeedbacksAfterFilter() {
        List<String> list = List.of(
                "Status"
        );
        createValidFeedback();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllFeedbacksCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        List<String> list = List.of(
                "New"
        );
        createValidFeedback();
        Assertions.assertDoesNotThrow(
                () -> listAllFeedbacksCommand.execute(list)
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
