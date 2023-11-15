package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FilterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllFeedbacksCommand implements Command {

    /** Command format: List_All_Feedbacks {filter status / ALL_STATUSES} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String NO_FEEDBACKS_ERROR = "There are currently no Feedbacks.";
    public static final String FEEDBACK_HEADER = "Feedbacks: ";
    public static final String SEPARATOR = "-".repeat(14);



    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ListAllFeedbacksCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String statusFilter = parameters.get(0);
        List<Bug> bugList = teamManagementRepository.getBugs();
        List<Bug> filteredBugList = new ArrayList<>();
        FilterHelpers.filterBugsByStatus(statusFilter, bugList, filteredBugList);

        if (teamManagementRepository.getFeedbacks().isEmpty()) {
            throw new IllegalArgumentException(NO_FEEDBACKS_ERROR);
        }

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(FEEDBACK_HEADER).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Feedback feedback : teamManagementRepository.getFeedbacks()) {
            output.append(feedback.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    /* TODO Like AllBugsCommand we can print with a header point what filters are choosen */
}