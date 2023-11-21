package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;

import java.util.List;

public class ShowAllPeopleCommand implements Command {
    /**
     * Command format: Show_All_People
     */
    private static final String NO_PEOPLE_ERROR = "There are currently no registered members.";
    private static final String MEMBERS_HEADER = "All registered members: ";
    private static final String SEPARATOR = "-".repeat(14);

    private final TeamManagementRepository teamManagementRepository;

    public ShowAllPeopleCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (teamManagementRepository.getMembers().isEmpty()) {
            throw new IllegalArgumentException(NO_PEOPLE_ERROR);
        }
        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append(System.lineSeparator());
        output.append(MEMBERS_HEADER).append(System.lineSeparator());
        output.append(SEPARATOR).append(System.lineSeparator());
        for (Member member : teamManagementRepository.getMembers()) {
            output.append(member.getName()).append(System.lineSeparator());
        }
        output.append(SEPARATOR)
                .append(System.lineSeparator());
        return output.toString().trim();
    }
}