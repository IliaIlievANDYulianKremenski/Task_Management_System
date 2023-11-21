package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ManualCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ManualCommandTests {
    private ManualCommand manualCommand;

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        TeamManagementRepository teamManagementRepository = new TeamManagementRepositoryImpl();
        manualCommand = new ManualCommand(teamManagementRepository);
        List<String> list = TestUtilities.createDesiredList(0);
        Assertions.assertDoesNotThrow(
                () -> manualCommand.execute(list)
        );
    }
}
