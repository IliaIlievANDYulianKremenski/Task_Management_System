package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllBugsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllFeedbacksCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ListAllFeedbacksCommandTests {

    /*<-------Constant(s)------->*/

    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllFeedbacksCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private ListAllFeedbacksCommand listAllFeedbacksCommand;

    /*Arrange*/
    @BeforeEach
    public void setListAllFeedbacksCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listAllFeedbacksCommand = new ListAllFeedbacksCommand(teamManagementRepository);

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
                () -> listAllFeedbacksCommand.execute(list)
        );

    }
    @Test
    public void should_ThrowException_When_THereAreNoFeedbacksToList() {
        /*Arrange*/
        List<String> list = List.of(
                "ALL_STATUSES"
        );
        /*Arrange,Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllFeedbacksCommand.execute(list)
        );

    }
    @Test
    public void should_ThrowException_When_THereAreNoFeedbacksAfterFilter() {
        /*Arrange*/
        List<String> list = List.of(
                "ALL_STATUSES"
        );
        Feedback feedback = createValidFeedback();
        /*Arrange,Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllFeedbacksCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        /*Arrange*/
        List<String> list = List.of(
                "New"
        );
        /*Act*/
        Feedback feedback = createValidFeedback();
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> listAllFeedbacksCommand.execute(list)
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
