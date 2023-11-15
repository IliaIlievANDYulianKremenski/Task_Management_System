package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {
    /**
     * Command format: Show_All_Team_Boards {team name}
     * */

    /*TODO - this command is supposed to list only the names of all boards which belong to a specific team
    *  or the name of every board and the tasks which have been assigned to them?*/

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String NO_BOARDS_ERROR = "There are no Boards to display.";



    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ShowAllTeamBoardsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        Team temporaryTeam = teamManagementRepository.findTeamByName(teamName);

        if (temporaryTeam.getTeamBoards().isEmpty()) {
            throw new IllegalArgumentException(NO_BOARDS_ERROR);
        }

        StringBuilder result = new StringBuilder();
        result.append("Boards: ").append(System.lineSeparator());
        for (Board board : temporaryTeam.getTeamBoards()) {
            result.append(board.getName()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
