package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddTaskToBoardCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddTaskToBoardCommandTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            AddTaskToBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private AddTaskToBoardCommand addTaskToBoardCommand;

    /*Arrange*/
    @BeforeEach
    public void setAddTaskToBoardCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        addTaskToBoardCommand = new AddTaskToBoardCommand(teamManagementRepository);
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
                () -> addTaskToBoardCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_WhenTaskIdNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "Task ID",
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> addTaskToBoardCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_AddTaskToBoard_When_PassedValidInput() {
        /*Arrange*/
        Bug bug = createValidBug();
        Team team = createValidTeam();
        Board board = createValidBoard();
        List<String> list = List.of(
                "1",
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
        /*Act*/
        addTaskToBoardCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                1,
                board.getBoardTasks().size()
        );
}
    private Bug createValidBug() {
        Member member = createValidMember();
        return teamManagementRepository.createBug(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                BugPriorityType.HIGH,
                BugSeverityType.CRITICAL,
                member
        );
    }
    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
    }
    private Board createValidBoard() {
        return teamManagementRepository.creteBoard(
                "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN),
                "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN)
        );
    }
    private Team createValidTeam() {
        return teamManagementRepository.createTeam("A".repeat(TeamImpl.TEAM_NAME_MIN_LEN));
    }

}


