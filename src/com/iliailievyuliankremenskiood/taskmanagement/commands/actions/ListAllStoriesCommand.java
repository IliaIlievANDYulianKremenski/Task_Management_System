package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;

import java.util.List;

public class ListAllStoriesCommand implements Command {

    /** Command format: List_All_Stories {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}*/

    /*<-------Constant(s)------->*/
    public static final String NO_STORIES_ERROR = "There are currently no Stories.";

    public static final String STORIES_HEADER = "Stories: ";
    public static final String SEPARATOR = "-".repeat(14);

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public ListAllStoriesCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        if (teamManagementRepository.getStories().isEmpty()) {
            throw new IllegalArgumentException(NO_STORIES_ERROR);
        }

        /*✏️ TODO ✏️- for Iliya to implement this part of the function.*/
        /*switch (parameters.size()){
            case 0 *//*All Feedbacks*//*:
                break;
            case 1 *//*Status*//*:
                break;
            case 2 *//*Status + Assignee*//*:
                break;
        }*/

        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(STORIES_HEADER).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Story story : teamManagementRepository.getStories()) {
            output.append(story.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    /*TODO Ask Yuli if I'm using his print() method correctly. My suggestion for his implementation is to remove the "Story" part and to have only 1 "Story" header
    implemented here. */
}