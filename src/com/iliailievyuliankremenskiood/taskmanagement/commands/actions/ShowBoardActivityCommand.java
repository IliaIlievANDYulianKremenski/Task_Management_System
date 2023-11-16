package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand implements Command {

    /* ✏️ TODO ✏️ To ask trainers: What we have to show here: all activities in a board of a team, just any board or absolutely all boards. */

    /** Command format: Show_Board_Activity {board name} {team name} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ShowBoardActivityCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);
        String teamName = parameters.get(1);

        Board board = teamManagementRepository.findBoardByName(boardName, teamName);

        return board.getActivityInfo();
    }
}