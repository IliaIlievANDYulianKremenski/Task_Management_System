package com.iliailievyuliankremenskiood.oop.taskmanagement;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;

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
    }

}