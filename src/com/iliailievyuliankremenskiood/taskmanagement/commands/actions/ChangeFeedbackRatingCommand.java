package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackRatingCommand implements Command {

    /** Command format: Change_Feedback_Rating {feedback ID} {feedback rating}*/

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ChangeFeedbackRatingCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int feedbackId = ParsingHelpers.parseInteger(parameters.get(0),"Feedback ID");
        int feedbackRating = ParsingHelpers.parseInteger(parameters.get(0),"Feedback Rating");

        Feedback feedback = teamManagementRepository.findFeedbackById(feedbackId);
        feedback.changeRating(feedbackRating);

        return userOutput(feedback);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Feedback feedback) {
        return feedback.getActivityHistory().get(feedback.getActivityHistory().size() - 1);
    }
}