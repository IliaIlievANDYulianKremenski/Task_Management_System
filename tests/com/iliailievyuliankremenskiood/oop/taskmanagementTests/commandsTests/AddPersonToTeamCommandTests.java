package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddCommentToStoryCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddPersonToTeamCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddPersonToTeamCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_TEAM_NAME = "a".repeat(TeamImpl.TEAM_NAME_MIN_LEN + 1);
    private static final String VALID_TEAM_NAME2 = "b".repeat(TeamImpl.TEAM_NAME_MIN_LEN + 1);
    private static final String INVALID_TEAM_NAME = "a".repeat(TeamImpl.TEAM_NAME_MIN_LEN - 1);
    private static final String VALID_TEAM_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_TEAM_MEMBER_NAME2 = "b".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String INVALID_TEAM_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN - 1);


    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private AddPersonToTeamCommand addPersonToTeamCommand;
    private List<String> parameters;
    private Team team;
    private Member member;


    /*<-------Behavioural Method(s)------->*/
    @BeforeEach
    private void setUp(){
        teamManagementRepository = new TeamManagementRepositoryImpl();
        addPersonToTeamCommand = new AddPersonToTeamCommand(teamManagementRepository);
        team = teamManagementRepository.createTeam(VALID_TEAM_NAME);
        member = teamManagementRepository.createMember(VALID_TEAM_MEMBER_NAME);

        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowException_When_ListWithInvalidNumberOfParamsPassed(){
        /*Arrange*/
        parameters.add("1");

        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {addPersonToTeamCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidTeamNamePassed(){
        /*Arrange*/
        parameters.add(VALID_TEAM_MEMBER_NAME);
        parameters.add(VALID_TEAM_NAME2);



        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> {addPersonToTeamCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidMemberNamePassed(){
        /*Arrange*/
        parameters.add(VALID_TEAM_MEMBER_NAME2);
        parameters.add(VALID_TEAM_NAME);

        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> {addPersonToTeamCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_executeSuccessfully_When_validParamsPassed(){
        /*Arrange*/
        parameters.add(VALID_TEAM_MEMBER_NAME);
        parameters.add(VALID_TEAM_NAME);

        /*Act*/
        String resultFromTeamMemberAddition = addPersonToTeamCommand.execute(parameters);

        /*Assert*/
        Assertions.assertEquals(
                team.getActivityHistory().get(team.getActivityHistory().size()-1),
                resultFromTeamMemberAddition);
    }
}
