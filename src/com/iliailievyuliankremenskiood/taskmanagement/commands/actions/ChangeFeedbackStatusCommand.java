package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackStatusCommand implements Command {
    /**
     * Command format: Change_Feedback_Status {feedback ID} {new status}
     */

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_FEEDBACK_STATUS_MESSAGE =
            "Invalid value for Feedback Status: %s. Should be NEW, UNSCHEDULED, SCHEDULED or DONE.";


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ChangeFeedbackStatusCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int feedbackId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Feedback ID"
        );
        
        FeedbackStatusType newStatus = ParsingHelpers.parseEnum(
                parameters.get(1),
                FeedbackStatusType.class,
                INVALID_FEEDBACK_STATUS_MESSAGE);

        Feedback temporaryFeedback = teamManagementRepository.findFeedbackById(feedbackId);
        temporaryFeedback.changeStatus(newStatus);

        return userOutput(temporaryFeedback);
    }

    private static String userOutput(Feedback feedbackWhoseActivityLogWeNeed) {
        return feedbackWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(feedbackWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
