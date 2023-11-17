package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowTeamActivityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamActivityCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_TEAM_NAME = "a".repeat(TeamImpl.TEAM_NAME_MIN_LEN);


    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private ShowTeamActivityCommand showTeamActivityCommand;
    private List<String> parameters;


    /*<-------Behavioural Method(s)------->*/
    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showTeamActivityCommand = new ShowTeamActivityCommand(teamManagementRepository);
        parameters = new ArrayList<>();

    }

    @Test
    public void execute_Should_ThrowException_When_InvalidNumberOfParamsPassed() {
        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    showTeamActivityCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_NotThrowExceptions_When_AValidTeamNamePassed() {
        /*Arrange*/
        teamManagementRepository.createTeam(VALID_TEAM_NAME);
        parameters.add(VALID_TEAM_NAME);

        /*Act, Assert*/
        Assertions.assertDoesNotThrow(() -> {
            showTeamActivityCommand.execute(parameters);
        });
    }
}
