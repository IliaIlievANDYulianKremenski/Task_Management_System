package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllFeedbacksCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowBoardActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowPersonActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowPersonActivityCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ShowBoardActivityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ShowPersonActivityCommand showPersonActivityCommand;

    @BeforeEach
    public void setShowPersonActivityCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showPersonActivityCommand = new ShowPersonActivityCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showPersonActivityCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoPersonToShow() {
        List<String> list = List.of(
                "Person name"
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> showPersonActivityCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        List<String> list = List.of(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        Member member = createValidMember();
        Assertions.assertDoesNotThrow(
                () -> showPersonActivityCommand.execute(list)
        );
    }

    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
    }
}
