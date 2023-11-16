package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllBugsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowBoardActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowBoardActivityCommandTests {

    /*<-------Constant(s)------->*/

    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ShowBoardActivityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private ShowBoardActivityCommand showBoardActivityCommand;

    /*Arrange*/
    @BeforeEach
    public void setCreateNewBugCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showBoardActivityCommand = new ShowBoardActivityCommand(teamManagementRepository);

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
                () -> showBoardActivityCommand.execute(list)
        );

    }
    @Test
    public void should_ThrowException_When_THereAreNoBoardsToShow() {
        /*Arrange*/
        List<String> list = List.of(
                "Board name",
                "Team name"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> showBoardActivityCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        /*Arrange*/
        List<String> list = List.of(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        /*Act*/
        Team team = createValidTeam();
        Board board = createValidBoard();
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> showBoardActivityCommand.execute(list)
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
