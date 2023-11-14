package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;

import java.util.List;

public class ChangeFeedbackStatusCommand implements Command {
    private final TeamManagementRepository teamManagementRepository;

    public ChangeFeedbackStatusCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
