package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewFeedbackCommand implements Command {
    /**
     * Command format: Create_New_Feedback {title} {description} {rating} {status}
     */
    private static final String INVALID_FEEDBACK_STATUS_MESSAGE =
            "Invalid value for Feedback Status: %s. Should be NEW, UNSCHEDULED, SCHEDULED or DONE.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    private final TeamManagementRepository teamManagementRepository;

    public CreateNewFeedbackCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        int feedbackRating = ParsingHelpers.parseInteger(
                parameters.get(2),
                "Feedback Rating"
        );
        FeedbackStatusType feedbackStatusType = ParsingHelpers.parseEnum(
                parameters.get(3),
                FeedbackStatusType.class,
                INVALID_FEEDBACK_STATUS_MESSAGE
        );
        Feedback feedback = teamManagementRepository.createFeedback(
                title,
                description,
                feedbackRating,
                feedbackStatusType
        );
        return userOutput(feedback);
    }

    private static String userOutput(Feedback feedback) {
        return feedback.getActivityHistory().get(feedback.getActivityHistory().size() - 1);
    }
}