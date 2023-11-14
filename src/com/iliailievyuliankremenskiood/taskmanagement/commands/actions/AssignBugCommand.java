package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ParsingHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class AssignBugCommand implements Command {

    /*TODO How do we get the input for the command. Does the id of the
    * task will be the second thing in the input line and the assignee
    * will be the third thing, or will be vice versa.
     */
    //Assign_Bug 50 pesho

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;


    private final TeamManagementRepository teamManagementRepository;

    public AssignBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int bugId = ParsingHelpers.parseInteger(parameters.get(0),"Bug ID");
        String memberName = parameters.get(1);

        Task task = teamManagementRepository.findTaskById(bugId);
        Bug bug = null;
        if (task instanceof Bug) {
            bug = (Bug) task;
        }
        Member member = teamManagementRepository.findMemberByName(parameters.get(1));

        bug.changeAssignee(member);

        return bug.getAssignee().getName();
    }
}
