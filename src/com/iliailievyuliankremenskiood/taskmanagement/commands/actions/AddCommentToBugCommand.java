package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.CommentImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;

import java.util.List;

public class AddCommentToBugCommand implements Command {

    private final TeamManagementRepository teamManagementRepository;

    public AddCommentToBugCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        Member member = new MemberImpl("NAMEEE");
        Bug bug = new BugImpl(1,"TItleee","Description", BugPriorityType.HIGH, BugSeverityType.CRITICAL,member);
        Comment comment = new CommentImpl("Author","Message");
        bug.addCommentToTask(comment);

        return null;
    }
}
