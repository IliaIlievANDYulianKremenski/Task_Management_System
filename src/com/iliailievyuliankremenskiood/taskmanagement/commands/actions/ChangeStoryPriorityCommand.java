package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryPriorityCommand implements Command {
    /**
     * Command format: Change_Story_Priority {story ID} {new priority}
     */

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_STORY_PRIORITY_MESSAGE =
            "Invalid value for Story Priority: %s. Should be HIGH, MEDIUM or LOW.";


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ChangeStoryPriorityCommand(TeamManagementRepository teamManagementRepository) {
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

        StoryPriorityType newType = ParsingHelpers.parseEnum(
                parameters.get(1),
                StoryPriorityType.class,
                INVALID_STORY_PRIORITY_MESSAGE);

        Story temporaryStory = teamManagementRepository.findStoryById(storyId);
        temporaryStory.changePriority(newType);

        return userOutput(temporaryStory);
    }

    private static String userOutput(Story storyWhoseActivityLogWeNeed) {
        return storyWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(storyWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
