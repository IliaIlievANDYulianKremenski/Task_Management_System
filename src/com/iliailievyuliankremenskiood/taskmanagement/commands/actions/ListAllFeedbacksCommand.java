package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;

import java.util.List;

public class ListAllFeedbacksCommand implements Command {

    /* TODO Discuss with Yuli do we need to validate 0 parameters, or it is the command that will be important and
    it will does not matter any text after it. */

    /** Command format: List_All_Feedbacks */

    /*<-------Constant(s)------->*/
    public static final String NO_FEEDBACKS_ERROR = "There are currently no Feedbacks.";


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

        StringBuilder output = new StringBuilder();
        for (Feedback feedback : teamManagementRepository.getFeedbacks()) {
            output.append(feedback.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    /*TODO Ask Yuli if I'm using his print() method correctly. My suggestion for his implementation is to remove the "Feedback" part and to have only 1 "Feedback" header
    implemented here. */
}