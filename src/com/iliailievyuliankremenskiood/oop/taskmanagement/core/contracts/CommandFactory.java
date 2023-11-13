package com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts;

import com.iliailievyuliankremenskiood.oop.taskmanagement.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(
            String commandTypeAsString,
            TeamManagementRepository teamManagementRepository);

}
