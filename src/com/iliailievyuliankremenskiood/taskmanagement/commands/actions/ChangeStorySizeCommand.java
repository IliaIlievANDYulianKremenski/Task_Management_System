package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeStorySizeCommand implements Command {
    /**
     * Command format: Change_Story_Size {story ID} {new size}
     */

    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_STORY_SIZE_MESSAGE =
            "Invalid value for Story Size: %s. Should be LARGE, MEDIUM or SMALL.";


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public ChangeStorySizeCommand(TeamManagementRepository teamManagementRepository) {
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
        StorySizeType newSize = ParsingHelpers.parseEnum(parameters.get(1),
                StorySizeType.class,
                INVALID_STORY_SIZE_MESSAGE);

        Story temporaryStory = teamManagementRepository.findStoryById(storyId);
        temporaryStory.changeSize(newSize);

        return userOutput(temporaryStory);
    }

    private static String userOutput(Story storyWhoseActivityLogWeNeed) {
        return storyWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(storyWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
