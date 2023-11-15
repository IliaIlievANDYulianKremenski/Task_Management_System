package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;

import java.util.List;

public class ListAllStoriesCommand implements Command {

     /* TODO Discuss with Yuli do we need to validate 0 parameters, or it is the command that will be important and
    it will does not matter any text after it. */

    /** Command format: List_All_Stories */

    /*<-------Constant(s)------->*/
    public static final String NO_STORIES_ERROR = "There are currently no Stories.";

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

        StringBuilder output = new StringBuilder();
        for (Story story : teamManagementRepository.getStories()) {
            output.append(story.print()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    /*TODO Ask Yuli if I'm using his print() method correctly. My suggestion for his implementation is to remove the "Story" part and to have only 1 "Story" header
    implemented here. */
}