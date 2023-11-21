package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AssignStoryCommand implements Command {
    /**
     * Command format: Assign_Story {story ID} {assignee}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TeamManagementRepository teamManagementRepository;

    public AssignStoryCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.parseInteger(
                parameters.get(0),
                "Story ID"
        );
        String memberName = parameters.get(1);
        Story temproaryStory = teamManagementRepository.findStoryById(storyId);
        Member temporaryMember = teamManagementRepository.findMemberByName(memberName);
        temproaryStory.changeAssignee(temporaryMember);
        return userOutput(temproaryStory);
    }

    private static String userOutput(Story storyWhoseActivityLogWeNeed) {
        return storyWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(storyWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
