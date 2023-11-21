package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllBugsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowAllTeamBoardsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllTeamBoardsTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllBugsCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ShowAllTeamBoardsCommand showAllTeamBoardsCommand;

    @BeforeEach
    public void setShowAllTeamBoardsCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showAllTeamBoardsCommand = new ShowAllTeamBoardsCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showAllTeamBoardsCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowAnException_When_ThereAreNoTeamWithTheProvidedName() {
        List<String> list = List.of(
                "Team"
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> showAllTeamBoardsCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowAnException_When_ThereAreNoBoardsInTheTeam() {
        List<String> list = List.of(
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        createValidTeam();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showAllTeamBoardsCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        List<String> list = List.of(
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN),
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN)
        );
        createValidTeam();
        createValidBoard();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showAllTeamBoardsCommand.execute(list)
        );
    }

    private Board createValidBoard() {
        return teamManagementRepository.creteBoard(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
    }

    private Team createValidTeam() {
        return teamManagementRepository.createTeam(
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
    }
}
