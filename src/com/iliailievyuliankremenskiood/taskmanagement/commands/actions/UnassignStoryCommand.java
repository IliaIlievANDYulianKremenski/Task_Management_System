package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class UnassignStoryCommand implements Command {
    /**
     * Command format: Unassign_Story {story ID}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String STORY_UNASSIGN_MESSAGE = "Story with ID #%d is now not assigned.";
    private final TeamManagementRepository teamManagementRepository;

    public UnassignStoryCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Story ID"
        );
        Story story = teamManagementRepository.findStoryById(storyId);
        story.changeAssignee(null);
        return userOutput(story);
    }

    private static String userOutput(Story story) {
        return String.format(STORY_UNASSIGN_MESSAGE, story.getId());
    }
}