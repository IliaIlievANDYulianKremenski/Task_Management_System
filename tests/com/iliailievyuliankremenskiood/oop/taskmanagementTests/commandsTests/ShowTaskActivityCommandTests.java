package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowBoardActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowPersonActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowTaskActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowTaskActivityCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ShowBoardActivityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ShowTaskActivityCommand showTaskActivityCommand;

    @BeforeEach
    public void setShowPersonActivityCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showTaskActivityCommand = new ShowTaskActivityCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showTaskActivityCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_BugIdNotNumber() {
        List<String> list = List.of(
                "Bug ID"
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> showTaskActivityCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        List<String> list = List.of(
                "1"
        );
        createValidFeedback();
        Assertions.assertDoesNotThrow(
                () -> showTaskActivityCommand.execute(list)
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
