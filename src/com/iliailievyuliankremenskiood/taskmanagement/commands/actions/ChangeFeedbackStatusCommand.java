package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;

import java.util.List;

public class ChangeFeedbackStatusCommand implements Command {

    private static final String INVALID_FEEDBACK_STATUS_MESSAGE = "Invalid value for Feedback Status: %s. Should be NEW, UNSCHEDULED, SCHEDULED or DONE.";

    private final TeamManagementRepository teamManagementRepository;

    public ChangeFeedbackStatusCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
