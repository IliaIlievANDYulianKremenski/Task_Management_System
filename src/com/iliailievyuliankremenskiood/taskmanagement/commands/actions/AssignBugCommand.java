package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AssignBugCommand implements Command {

    /** Command format: Assign_Bug {bug ID} {assignee} */

    /*<-------Constant(s)------->*/

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    /*<-------Field(s)------->*/

    private final TeamManagementRepository teamManagementRepository;

    /*<-------Constructor(s)------->*/

    public AssignBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    /*<-------Behavioural Method(s)------->*/

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int bugId = ParsingHelpers.parseInteger(parameters.get(0),"Bug ID");
        String memberName = parameters.get(1);

        Bug bug = teamManagementRepository.findBugById(bugId);
        Member member = teamManagementRepository.findMemberByName(memberName);

        bug.changeAssignee(member);

        return userOutput(bug);
    }

    /*<-------Helper Method(s)------->*/

    private static String userOutput(Bug bug) {
        return bug.getActivityHistory().get(bug.getActivityHistory().size() - 1);
    }
}
