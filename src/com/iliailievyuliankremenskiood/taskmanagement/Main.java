package com.iliailievyuliankremenskiood.taskmanagement;

import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.BoardImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FilterHelpers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*Needed this test for the FeedbackImpl.print().
        Feedback feedback = new FeedbackImpl(1,
                "Something, something darkSid.",
                "Description should be something that is long.", 1, FeedbackStatusType.NEW);
         System.out.println(feedback.print());
        System.out.println(feedback.getActivityHistory());
        System.out.println("=".repeat(30));
        feedback.changeStatus(FeedbackStatusType.SCHEDULED);
        feedback.changeRating(1234567);
        System.out.println(feedback.getActivityHistory());*/

/*      Member member = new MemberImpl("Юлиан Кременски");
        Bug bug = new BugImpl(1, "The program freezes when the Log In button is clicked.",
                "This needs to be fixed quickly!", BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
        System.out.println(bug.print());*/


/*        Member member = new MemberImpl("Юлиан Кременски");
        Story story = new StoryImpl(1, "Some random title.", "Some random description that is.",
                StoryPriorityType.HIGH, StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);
        System.out.println(story.print());*/


        //Ilia tests
//        Board board = new BoardImpl("String");
//        System.out.println(board.getActivityHistory().get(0));
        TeamManagementRepositoryImpl teamManagementRepository = new TeamManagementRepositoryImpl();
        List<String> list = List.of(
                "ALL_STATUSES",
                "ALL_ASSIGNEES"
        );

        String statusFilter = list.get(0);
        String assigneeFilter = list.get(1);

        Member member = teamManagementRepository.createMember("Member");
        teamManagementRepository.createBug(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                BugPriorityType.HIGH,
                BugSeverityType.CRITICAL,
                member
        );

        List<Bug> bugList = teamManagementRepository.getBugs();
        System.out.println(bugList.size());

        List<Bug> filteredBugList = FilterHelpers.filterBugsByStatus(
                statusFilter,
                bugList);
        System.out.println(filteredBugList.size());
        bugList = new ArrayList<>(filteredBugList);
        filteredBugList = FilterHelpers.filterBugsByAssignee(assigneeFilter, bugList);

        System.out.println(filteredBugList.size());

    }
}