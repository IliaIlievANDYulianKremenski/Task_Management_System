package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FilterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ListAllFeedbacksCommand implements Command {
    /**
     * Command format: List_All_Feedbacks {filter status / ALL_STATUSES}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String NO_FEEDBACKS_ERROR = "There are currently no Feedbacks with STATUS: %s";
    private static final String FEEDBACK_HEADER = "Feedbacks with STATUS: %s";
    private static final String SEPARATOR = "-".repeat(14);

    private final TeamManagementRepository teamManagementRepository;

    public ListAllFeedbacksCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String statusFilter = parameters.get(0);
        List<Feedback> feedbacksList = teamManagementRepository.getFeedbacks();
        feedbacksList = FilterHelpers.filterFeedbacksByStatus(
                statusFilter,
                feedbacksList,
                String.format(NO_FEEDBACKS_ERROR,statusFilter)
        );
        if (feedbacksList.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_FEEDBACKS_ERROR,statusFilter));
        }
        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(String.format(FEEDBACK_HEADER, statusFilter)).append(System.lineSeparator());
        for (Feedback feedback : feedbacksList) {
            output.append(feedback.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}