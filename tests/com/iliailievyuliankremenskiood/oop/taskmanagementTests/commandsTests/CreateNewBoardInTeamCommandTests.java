package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ChangeFeedbackRatingCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewBoardInTeamCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateNewBoardInTeamCommandTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            CreateNewBoardInTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private CreateNewBoardInTeamCommand createNewBoardInTeamCommand;

    /*Arrange*/
    @BeforeEach
    public void setChangeFeedbackRatingCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewBoardInTeamCommand = new CreateNewBoardInTeamCommand(teamManagementRepository);
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
                () -> createNewBoardInTeamCommand.execute(list)
        );

    }
    @Test
    public void should_ThrowException_When_NoSuchTeamToCreateTheBoard() {
        /*Arrange*/
        List<String> list = List.of(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> createNewBoardInTeamCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_CreateBoard_When_PassedValidInput() {
        /*Arrange*/
        Team team = createValidTeam();
        List<String> list = List.of(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        /*Act*/
        createNewBoardInTeamCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                1,
                team.getTeamBoards().size()
        );
        Assertions.assertEquals(
                1,
                teamManagementRepository.getTeams().get(0).getTeamBoards().size()
        );

    }

    /*<-------Helper Method(s)------->*/

    private Team createValidTeam() {
        return teamManagementRepository.createTeam(
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN));
    }

}
