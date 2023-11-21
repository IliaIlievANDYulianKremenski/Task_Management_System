package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBoardInTeamCommand implements Command {
    /**
     * Command format: Create_New_Board_In_Team {board name} {team name}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TeamManagementRepository teamManagementRepository;

    public CreateNewBoardInTeamCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        Board board = teamManagementRepository.creteBoard(boardName, teamName);
        Team team = teamManagementRepository.findTeamByName(teamName);
        return userOutput(team);
    }

    private static String userOutput(Team team) {
        return team.getActivityHistory().get(team.getActivityHistory().size() - 1);
    }
}

