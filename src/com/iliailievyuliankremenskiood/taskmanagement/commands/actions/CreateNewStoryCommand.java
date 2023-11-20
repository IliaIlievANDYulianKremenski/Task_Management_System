package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewStoryCommand implements Command {
    /**
     * Command format: Create_New_Story {title} {description} {priority} {size} {status} {optional: assignee}
     */

    /*<-------Constant(s)------->*/
    private static final String INVALID_STORY_PRIORITY_MESSAGE =
            "Invalid value for Story Priority: %s. Should be HIGH, MEDIUM or LOW.";
    private static final String INVALID_STORY_SIZE_MESSAGE =
            "Invalid value for Story Size: %s. Should be LARGE, MEDIUM or SMALL.";
    private static final String INVALID_STORY_STATUS_MESSAGE =
            "Invalid value for Story Status: %s. Should be NOT_DONE, IN_PROGRESS or DONE.";

    public static final int NUMBER_WITH_ASSIGNEE = 6;
    public static final int NUMBER_WITHOUT_ASSIGNEE = 5;


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public CreateNewStoryCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateTaskWithAssigneeCount(parameters,NUMBER_WITH_ASSIGNEE,NUMBER_WITHOUT_ASSIGNEE);

        String storyTitle = parameters.get(0);
        String storyDescription = parameters.get(1);


        StoryPriorityType storyPriorityType = ParsingHelpers.parseEnum(
                parameters.get(2),
                StoryPriorityType.class,
                INVALID_STORY_PRIORITY_MESSAGE
        );

        StorySizeType storySizeType = ParsingHelpers.parseEnum(
                parameters.get(3),
                StorySizeType.class,
                INVALID_STORY_SIZE_MESSAGE
        );

        StoryStatusType storyStatusType = ParsingHelpers.parseEnum(
                parameters.get(4),
                StoryStatusType.class,
                INVALID_STORY_STATUS_MESSAGE
        );

        Member assignee = null;
        if (parameters.size() == NUMBER_WITH_ASSIGNEE) {
            String assigneeName = parameters.get(5);
            assignee = teamManagementRepository.findMemberByName(assigneeName);
        }

        Story temporarStory = teamManagementRepository.createStory(
                storyTitle,
                storyDescription,
                storyPriorityType,
                storySizeType,
                storyStatusType,
                assignee);

        return userOutput(temporarStory);
    }

    private static String userOutput(Story storyWhoseActivityLogWeNeed) {
        return storyWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(storyWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
