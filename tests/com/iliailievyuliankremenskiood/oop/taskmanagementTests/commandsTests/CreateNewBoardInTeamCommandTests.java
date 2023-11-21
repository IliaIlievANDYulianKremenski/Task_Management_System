package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewBoardInTeamCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateNewBoardInTeamCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            CreateNewBoardInTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private CreateNewBoardInTeamCommand createNewBoardInTeamCommand;

    @BeforeEach
    public void setCreateNewBoardInTeamCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewBoardInTeamCommand = new CreateNewBoardInTeamCommand(teamManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createNewBoardInTeamCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_NoSuchTeamToCreateTheBoard() {
        List<String> list = List.of(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> createNewBoardInTeamCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_CreateBoard_When_PassedValidInput() {
        Team team = createValidTeam();
        List<String> list = List.of(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        createNewBoardInTeamCommand.execute(list);
        Assertions.assertEquals(
                1,
                team.getTeamBoards().size()
        );
        Assertions.assertEquals(
                1,
                teamManagementRepository.getTeams().get(0).getTeamBoards().size()
        );
    }

    private Team createValidTeam() {
        return teamManagementRepository.createTeam(
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN));
    }
}
