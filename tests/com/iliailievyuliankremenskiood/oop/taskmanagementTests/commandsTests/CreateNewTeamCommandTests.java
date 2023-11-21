package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewTeamCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewTeamCommandTests {
    private static final String VALID_TEAM_NAME = "a".repeat(TeamImpl.TEAM_NAME_MIN_LEN + 1);
    private TeamManagementRepository teamManagementRepository;
    private CreateNewTeamCommand createNewTeamCommand;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewTeamCommand = new CreateNewTeamCommand(teamManagementRepository);
        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowAnException_When_InvalidNumberOfParamsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createNewTeamCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_CreateAValidNewTeam_When_ValidNumberOfParamsPassed() {
        parameters.add(VALID_TEAM_NAME);
        String resultFromSuccessfulTeamCreation = createNewTeamCommand.execute(parameters);
        Assertions.assertEquals(
                teamManagementRepository.findTeamByName(VALID_TEAM_NAME).getActivityHistory().get(
                        teamManagementRepository.findTeamByName(VALID_TEAM_NAME).getActivityHistory().size() - 1),
                resultFromSuccessfulTeamCreation
        );
    }
}
