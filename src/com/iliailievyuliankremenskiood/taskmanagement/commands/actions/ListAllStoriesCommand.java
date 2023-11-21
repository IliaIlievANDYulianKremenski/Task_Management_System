package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FilterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ListAllStoriesCommand implements Command {
    /**
     * Command format: List_All_Stories {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String NO_STORIES_ERROR = "There are currently no Stories.";
    private static final String STORIES_HEADER = "Stories with STATUS: %s and ASSIGNEE: %s";
    private static final String SEPARATOR = "-".repeat(14);
    private final TeamManagementRepository teamManagementRepository;

    public ListAllStoriesCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String statusFilter = parameters.get(0);
        String assigneeFilter = parameters.get(1);
        List<Story> storyList = teamManagementRepository.getStories();
        storyList = FilterHelpers.filterStoriesByStatus(
                statusFilter,
                storyList);
        storyList = FilterHelpers.filterTasksByAssignee(
                assigneeFilter,
                storyList
        );
        if (storyList.isEmpty()) {
            throw new IllegalArgumentException(NO_STORIES_ERROR);
        }
        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(String.format(STORIES_HEADER, statusFilter, assigneeFilter)).append(System.lineSeparator());
        for (Story story : storyList) {
            output.append(story.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}