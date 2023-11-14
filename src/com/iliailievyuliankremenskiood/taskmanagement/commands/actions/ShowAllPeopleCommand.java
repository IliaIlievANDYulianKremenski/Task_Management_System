package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;

import java.util.List;

public class ShowAllPeopleCommand implements Command {

     /* TODO Discuss with Yuli do we need to validate 0 parameters, or it is the command that will be important and
    it will does not matter any text after it. */

    /** Command format: Show_All_People */

    /*<-------Constant(s)------->*/
    public static final String NO_PEOPLE_ERROR = "There are currently no People.";

    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ShowAllPeopleCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        if (teamManagementRepository.getMembers().isEmpty()) {
            throw new IllegalArgumentException(NO_PEOPLE_ERROR);
        }

        StringBuilder output = new StringBuilder();
        for (Member member : teamManagementRepository.getMembers()) {
            output.append(member.getName()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}