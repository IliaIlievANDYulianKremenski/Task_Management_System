package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToFeedbackCommand implements Command {

    /**
     * Command format: Add_Comment_to_Feedback {feedback ID} {author} {comment}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TeamManagementRepository teamManagementRepository;

    public AddCommentToFeedbackCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int feedbackId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Feedback ID"
        );
        String author = parameters.get(1);
        String message = parameters.get(2);
        Feedback feedback = teamManagementRepository.findFeedbackById(feedbackId);
        Comment comment = teamManagementRepository.createComment(author, message);
        feedback.addCommentToTask(comment);
        return userOutput(feedback);
    }

    private static String userOutput(Feedback feedback) {
        return feedback.getActivityHistory().get(feedback.getActivityHistory().size() - 1);
    }
}