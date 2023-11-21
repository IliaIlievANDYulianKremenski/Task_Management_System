package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {
    /**
     * Command format: Show_All_Team_Boards {team name}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String NO_BOARDS_ERROR = "There are no boards to display.";

    private final TeamManagementRepository teamManagementRepository;

    public ShowAllTeamBoardsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        Team temporaryTeam = teamManagementRepository.findTeamByName(teamName);
        if (temporaryTeam.getTeamBoards().isEmpty()) {
            throw new IllegalArgumentException(NO_BOARDS_ERROR);
        }
        return temporaryTeam.showAllBoards();
    }
}
