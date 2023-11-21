package com.iliailievyuliankremenskiood.taskmanagement.core.contracts;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(
            String commandTypeAsString,
            TeamManagementRepository teamManagementRepository);
}
