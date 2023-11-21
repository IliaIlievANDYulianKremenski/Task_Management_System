package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToBugCommand implements Command {

    /*✏️ TODO ✏️- do the trainers want us to make the author a member, or is it OK to leave it this, so that
     *  even anonymous ppl can leave comments?*/
    /**
     * Command format: Add_Comment_to_Bug {bug ID} {author} {comment}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private final TeamManagementRepository teamManagementRepository;

    public AddCommentToBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Bug ID"
        );
        String author = parameters.get(1);
        String message = parameters.get(2);
        Bug bug = teamManagementRepository.findBugById(bugId);
        Comment comment = teamManagementRepository.createComment(author, message);
        bug.addCommentToTask(comment);
        return userOutput(bug);
    }

    private static String userOutput(Bug bug) {
        return bug.getActivityHistory().get(bug.getActivityHistory().size() - 1);
    }
}
