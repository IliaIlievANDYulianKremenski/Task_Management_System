package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;

import java.util.List;

public class ListAllBugsCommand implements Command {

    /* TODO Discuss with Yuli do we need to validate 0 parameters, or it is the command that will be important and
    it will does not matter any text after it. */

    /** Command format: List_All_Bugs */

    private final TeamManagementRepository teamManagementRepository;

    public ListAllBugsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        StringBuilder output = new StringBuilder();
        for (Bug bug : teamManagementRepository.getBugs()) {
            output.append(bug.print());
        }
        return output.toString().trim();
    }

    /*TODO Ask Yuli if I'm using his print() method correctly. My suggestion for his implementation is to remove the "Bug" part and to have only 1 "Bug" header
    implemented here. */
}
