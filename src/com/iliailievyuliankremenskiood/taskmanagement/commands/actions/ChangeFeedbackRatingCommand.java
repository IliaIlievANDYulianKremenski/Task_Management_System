package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;

import java.util.List;

public class ChangeFeedbackRatingCommand implements Command {
    private final TeamManagementRepository teamManagementRepository;

    public ChangeFeedbackRatingCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
