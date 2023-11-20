package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddTaskToBoardCommand implements Command {

    /** Command format: Add_Task_to_Board {task ID} {board name} {team name}  */

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public AddTaskToBoardCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Task ID"
        );
        Task task = teamManagementRepository.findTaskById(taskId);
        String boardName = parameters.get(1);
        String teamName = parameters.get(2);

        Board board = teamManagementRepository.findBoardByName(boardName, teamName);
        board.createTaskInBoard(task);

        /*TODO We can only add general task to board, not a specific one. */

        return userOutput(board);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Board board) {
        return board.getActivityHistory().get(board.getActivityHistory().size() - 1);
    }
}
