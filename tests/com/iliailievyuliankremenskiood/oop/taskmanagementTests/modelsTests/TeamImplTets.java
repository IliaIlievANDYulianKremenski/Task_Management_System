package com.iliailievyuliankremenskiood.oop.taskmanagementTests.modelsTests;

import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamImplTets {

    /*<-------Constant(s)------->*/
    public static final String VALID_NAME = "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN);
    public static final String SHORT_NAME = "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN-1);
    public static final String LONG_NAME = "A".repeat(TeamImpl.TEAM_NAME_MAX_LEN+1);


    /*<-------Field(s)------->*/

    private TeamImpl team;

    /*Arrange*/
    @BeforeEach
    public void setTeam() {
        team = new TeamImpl(VALID_NAME);
    }

    /*<-------Test(s)------->*/

    @Test
    public void constructor_Should_createTeam_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(VALID_NAME,team.getName());
    }
    @Test
    public void constructor_Should_initializeMembers_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(0,team.getTeamMembers().size());
    }
    @Test
    public void constructor_Should_initializeBoards_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(0,team.getTeamBoards().size());
    }
    @Test
    public void constructor_Should_logActivityHistory_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(1,team.getActivityHistory().size());
    }
    @Test
    public void constructor_Should_ThrowError_When_ShorterNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new TeamImpl(SHORT_NAME)
        );
    }
    @Test
    public void constructor_Should_ThrowError_When_LongerNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new TeamImpl(LONG_NAME)
        );
    }
    @Test
    public void getBoards_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertNotSame(team.getTeamBoards(), team.getTeamBoards());
    }
    @Test
    public void getMembers_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertNotSame(team.getTeamMembers(), team.getTeamMembers());
    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        /*Act, Assert*/
        Assertions.assertNotSame(team.getActivityHistory(), team.getActivityHistory());
    }
    @Test
    public void addMember_Should_AddMemberToTheCollection() {
        /*Arrange*/
        Member member = createValidMember();
        /*Act*/
        team.addMember(member);
        /*Assert*/
        Assertions.assertEquals(1,team.getTeamMembers().size());
    }
    @Test
    public void addMember_Should_AddHistoryLog() {
        /*Arrange*/
        Member member = createValidMember();
        /*Act*/
        team.addMember(member);
        /*Assert*/
        Assertions.assertEquals(2,team.getActivityHistory().size());
    }
    @Test
    public void removeMember_Should_RemoveMemberFromTheCollection() {
        /*Arrange*/
        Member member = createValidMember();
        /*Act*/
        team.addMember(member);
        team.removeMember(member);
        /*Assert*/
        Assertions.assertEquals(0,team.getTeamMembers().size());
    }
    @Test
    public void removeMember_Should_AddHistoryLog() {
        /*Arrange*/
        Member member = createValidMember();
        /*Act*/
        team.addMember(member);
        team.removeMember(member);
        /*Assert*/
        Assertions.assertEquals(3,team.getActivityHistory().size());
    }
    @Test
    public void createBoard_Should_AddBoardToTheCollection() {
        /*Arrange*/
        Board board = createValidBoard();
        /*Act*/
        team.createBoard(board);
        /*Assert*/
        Assertions.assertEquals(1,team.getTeamBoards().size());
    }
    @Test
    public void createBoard_Should_AddHistoryLog() {
        /*Arrange*/
        Board board = createValidBoard();
        /*Act*/
        team.createBoard(board);
        /*Assert*/
        Assertions.assertEquals(2,team.getActivityHistory().size());
    }
    @Test
    public void removeBoard_Should_RemoveBoardFromTheCollection() {
        /*Arrange*/
        Board board = createValidBoard();
        /*Act*/
        team.createBoard(board);
        team.removeBoard(board);
        /*Assert*/
        Assertions.assertEquals(0,team.getTeamBoards().size());
    }

    @Test
    public void removeBoard_should_AddHistoryLog() {
        /*Arrange*/
        Board board = createValidBoard();
        /*Act*/
        team.createBoard(board);
        team.removeBoard(board);
        /*Assert*/
        Assertions.assertEquals(3,team.getActivityHistory().size());
    }
    @Test
    public void showAllMembers_Should_NotThrowException_When_MethodCalled() {
        /*Arrange, Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> team.showAllMembers()
        );
    }
    @Test
    public void showAllMembers_Should_NotThrowException_When_MethodCalledAndThereAreMembers() {
        /*Arrange*/
        Member member = createValidMember();
        team.addMember(member);
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> team.showAllMembers()
        );
    }
    @Test
    public void showAllBoards_Should_NotThrowException_When_MethodCalled() {
        /*Arrange, Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> team.showAllBoards()
        );
    }
    @Test
    public void showAllBoards_Should_NotThrowException_When_MethodCalledAndThereAreBoards() {
       /*Arrange*/
        Board board = createValidBoard();
        team.createBoard(board);
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> team.showAllBoards()
        );
    }
    @Test
    public void getActivityInfo_Should_NotThrowException_When_MethodCalled() {
        /*Arrange, Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> team.getActivityInfo()
        );
    }

    /*<-------Helper Method(s)------->*/

    private static Member createValidMember() {
        return new MemberImpl(MemberImplTests.VALID_NAME);
    }
    private static Board createValidBoard() {
        return new BoardImpl(BoardImplTests.VALID_NAME);
    }
}
