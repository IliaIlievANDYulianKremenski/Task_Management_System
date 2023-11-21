package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowAllTeamMembersCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamMembersCommandTests {
    private static final String RANDOM_TEAM_NAME = "b".repeat(TeamImpl.TEAM_NAME_MIN_LEN);
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN);
    private TeamManagementRepository teamManagementRepository;
    private ShowAllTeamMembersCommand showAllTeamMembersCommand;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showAllTeamMembersCommand = new ShowAllTeamMembersCommand(teamManagementRepository);
        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowAnException_When_InvalidAmountOfParamsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    showAllTeamMembersCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowAnException_When_ThereIsNoTeamWithTheProvidedName() {
        parameters.add(RANDOM_TEAM_NAME);
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> {
                    showAllTeamMembersCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowAnException_When_ThereIsNobodyInTheTeam() {
        teamManagementRepository.createTeam(RANDOM_TEAM_NAME);
        parameters.add(RANDOM_TEAM_NAME);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    showAllTeamMembersCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_NotThrowAnException_When_ValidTeamNameProvided() {
        teamManagementRepository.createTeam(RANDOM_TEAM_NAME);
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        teamManagementRepository.findTeamByName(RANDOM_TEAM_NAME).addMember(member);
        parameters.add(RANDOM_TEAM_NAME);
        Assertions.assertDoesNotThrow(() -> {
            showAllTeamMembersCommand.execute(parameters);
        });
    }
}
