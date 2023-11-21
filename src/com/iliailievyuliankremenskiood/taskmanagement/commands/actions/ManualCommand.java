package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ManualCommandPrintHelper;

import java.util.List;

public class ManualCommand implements Command {
    /** Command format: Manual */
    private final TeamManagementRepository teamManagementRepository;

    public ManualCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return ManualCommandPrintHelper.print();
    }
}
