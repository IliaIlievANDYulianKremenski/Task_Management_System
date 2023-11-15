package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToStoryCommand implements Command {
    /**
     * Command format: Add_Comment_to_Story {story ID} {author} {comment}
     */
    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public AddCommentToStoryCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int storyId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Story ID"
        );
        String author = parameters.get(1);
        String message = parameters.get(2);

        Story temporaryStory = teamManagementRepository.findStoryById(storyId);
        Comment comment = teamManagementRepository.createComment(author, message);
        temporaryStory.addCommentToTask(comment);

        return userOutput(temporaryStory);
    }

    private static String userOutput(Story storyWhoseActivityLogWeNeed) {
        return storyWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(storyWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
