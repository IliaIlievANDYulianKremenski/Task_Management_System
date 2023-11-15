package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;

import java.util.List;

public class UnassignStoryCommand implements Command {

    /** Command format: Unassign_Story {story ID} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String STORY_UNASSIGN_MESSAGE = "Story with ID #%d is now not assigned.";

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public UnassignStoryCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }
    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {

        int storyId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Story ID"
        );

        Story story = teamManagementRepository.findStoryById(storyId);

        story.changeAssignee(null);

        return userOutput(story);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Story story) {
        return String.format(STORY_UNASSIGN_MESSAGE, story.getId());
    }
}