package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;

import java.util.List;

public class ListAllBugsCommand implements Command {

    /** Command format: List_All_Bugs {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}*/

    /*<-------Constant(s)------->*/
    public static final String NO_BUGS_ERROR = "There are currently no Bugs.";
    public static final String BUGS_HEADER = "Bugs: ";
    public static final String SEPARATOR = "-".repeat(14);


    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ListAllBugsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        if (teamManagementRepository.getBugs().isEmpty()) {
            throw new IllegalArgumentException(NO_BUGS_ERROR);
        }

        /*✏️ TODO ✏️- for Iliya to implement this part of the function.*/
        /*switch (parameters.size()){
            case 0 *//*All Bugs*//*:
                break;
            case 1 *//*Status*//*:
                break;
            case 2 *//*Status + Assignee*//*:
                break;
        }*/

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(BUGS_HEADER).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Bug bug : teamManagementRepository.getBugs()) {
            output.append(bug.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
