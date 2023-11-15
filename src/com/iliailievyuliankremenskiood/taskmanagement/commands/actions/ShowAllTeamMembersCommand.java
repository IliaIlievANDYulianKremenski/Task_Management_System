package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand implements Command {
    /**
     * Command format: Show_All_Team_Members {team name}
     */

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String NO_TEAM_MEMBERS_ERROR = "There are no team members in this team.";


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ShowAllTeamMembersCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        Team temporaryTeam = teamManagementRepository.findTeamByName(teamName);

        if (temporaryTeam.getTeamMembers().isEmpty()) {
            throw new IllegalArgumentException(NO_TEAM_MEMBERS_ERROR);
        }

        StringBuilder result = new StringBuilder();
        result.append("Team members: ").append(System.lineSeparator());
        for (Member member : temporaryTeam.getTeamMembers()) {
            result.append(member.getName()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
