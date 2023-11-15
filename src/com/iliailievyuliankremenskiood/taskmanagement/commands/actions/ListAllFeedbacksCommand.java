package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;

import java.util.List;

public class ListAllFeedbacksCommand implements Command {

    /** Command format: List_All_Feedbacks {filter status / ALL_STATUSES}*/

    /*<-------Constant(s)------->*/
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
        if (teamManagementRepository.getFeedbacks().isEmpty()) {
            throw new IllegalArgumentException(NO_FEEDBACKS_ERROR);
        }

        /*✏️ TODO ✏️- for Iliya to implement this part of the function.*/
        /*switch (parameters.size()){
            case 0 *//*All Feedbacks*//*:
                break;
            case 1 *//*Status*//*:
                break;
        }*/

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(FEEDBACK_HEADER).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Feedback feedback : teamManagementRepository.getFeedbacks()) {
            output.append(feedback.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}