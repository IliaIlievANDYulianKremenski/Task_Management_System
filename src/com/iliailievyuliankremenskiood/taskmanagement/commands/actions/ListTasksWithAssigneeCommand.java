package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;

import java.util.List;

public class ListTasksWithAssigneeCommand implements Command {
    /**
     * Command format: List_All_Tasks_With_Assignee {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
     * */

    private final TeamManagementRepository teamManagementRepository;

    public ListTasksWithAssigneeCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*✏️ TODO ✏️- for Yuli to implement this part of the function.
    *    Check command format for more info.*/


    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
