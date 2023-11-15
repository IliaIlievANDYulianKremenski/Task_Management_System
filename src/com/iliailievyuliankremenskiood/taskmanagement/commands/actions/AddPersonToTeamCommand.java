package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddPersonToTeamCommand implements Command {
    /**
     * Command format: Add_Person_to_Team {person name} {team name}
     */
    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public AddPersonToTeamCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String personName = parameters.get(0);
        String teamName = parameters.get(1);

        Member person = teamManagementRepository.findMemberByName(personName);
        Team team = teamManagementRepository.findTeamByName(teamName);

        team.addMember(person);
        return userOutput(team);
    }

    private static String userOutput(Team teamWhoseActivityLogWeNeed) {
        return teamWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(teamWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
