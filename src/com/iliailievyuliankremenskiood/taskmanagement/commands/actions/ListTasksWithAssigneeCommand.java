package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;

import java.util.List;

public class ListTasksWithAssigneeCommand implements Command {
    /*TODO - it is technically impossible to create a task without assigning it to somebody.
    *  This means that both List_All_Tasks and this command will be doing the same thing.
    *  Should we keep this one?*/
    private final TeamManagementRepository teamManagementRepository;

    public ListTasksWithAssigneeCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
