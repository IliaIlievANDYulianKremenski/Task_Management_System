package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;

import java.util.List;

public class ShowAllTeamsCommand implements Command {
    /**
     * Command format: Show_All_Teams
     */

    /*<-------Constant(s)------->*/
    public static final String NO_TEAMS_ERROR_MESSAGE = "There are no teams registered in the system.";
    public static final String TEAMS_HEADER = "Teams: ";
    public static final String SEPARATOR = "-".repeat(14);


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ShowAllTeamsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        if (teamManagementRepository.getTeams().isEmpty()) {
            throw new IllegalArgumentException(NO_TEAMS_ERROR_MESSAGE);
        }

        StringBuilder result = new StringBuilder();
        result.append(TEAMS_HEADER)
                .append(System.lineSeparator())
                .append(SEPARATOR)
                .append(System.lineSeparator());

        for (Team team : teamManagementRepository.getTeams()) {
            result.append(team.getName()).append(System.lineSeparator());
        }
        result.append(SEPARATOR).append(System.lineSeparator());

        return result.toString().trim();
    }
}
