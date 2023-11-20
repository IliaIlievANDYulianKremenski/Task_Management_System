package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTaskCommentsCommand implements Command {

    /** Command format: Show_All_Task_Comments {task ID}*/

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String NO_COMMENTS_ERROR = "This task currently has no comments.";
    private static final String HEADER = "All task #%d comments: ";
    private static final String SEPARATOR = "-".repeat(14);

    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/
    public ShowAllTaskCommentsCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Task ID"
        );
        Task task = teamManagementRepository.findTaskById(taskId);
        if(task.getComments().isEmpty()) {
            throw new IllegalArgumentException(NO_COMMENTS_ERROR);
        }

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(String.format(HEADER,taskId)).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Comment comment : task.getComments()) {
            output.append("Author: ")
                    .append(comment.getAuthor())
                    .append(System.lineSeparator()
                    );
            output.append("Comment: ")
                    .append(comment.getMessage()).
                    append(System.lineSeparator()
                    );
        }
        output.append(SEPARATOR).append(System.lineSeparator());
        return output.toString().trim();
    }
}
