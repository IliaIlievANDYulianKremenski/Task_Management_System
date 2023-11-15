package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewTeamCommand implements Command {
    /**
     * Command format: Create_New_Team {team name}
     * */
    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public CreateNewTeamCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        Team temporaryTeam = teamManagementRepository.createTeam(teamName);

        return userOutput(temporaryTeam);
    }

    private static String userOutput(Team teamWhoseActivityLogWeNeed) {
        return teamWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(teamWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
