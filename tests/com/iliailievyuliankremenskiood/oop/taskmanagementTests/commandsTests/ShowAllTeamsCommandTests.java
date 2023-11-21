package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowAllTeamsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.TeamImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsCommandTests {
    private static final String VALID_TEAM_NAME1 = "a".repeat(TeamImpl.TEAM_NAME_MIN_LEN);
    private static final String VALID_TEAM_NAME2 = "b".repeat(TeamImpl.TEAM_NAME_MIN_LEN);
    private TeamManagementRepository teamManagementRepository;
    private ShowAllTeamsCommand showAllTeamsCommand;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showAllTeamsCommand = new ShowAllTeamsCommand(teamManagementRepository);
        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowException_When_ThereAreNoTeamsInTheRepository() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    showAllTeamsCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_NotThrowException_When_ThereAreTeamsToBeShown() {
        teamManagementRepository.createTeam(VALID_TEAM_NAME1);
        teamManagementRepository.createTeam(VALID_TEAM_NAME2);
        Assertions.assertDoesNotThrow(
                () -> {
                    showAllTeamsCommand.execute(parameters);
                });
    }
}
