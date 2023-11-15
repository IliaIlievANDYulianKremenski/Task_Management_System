package com.iliailievyuliankremenskiood.taskmanagement.utils;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.*;

import java.util.List;

public class FilterHelpers {

    /* TODO Ask Yuli, which is better, filter methods to be void or to have return value? */

    public static void filterBugsByStatus(String statusFilter, List<Bug> bugList, List<Bug> filteredBugList) {
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Bug bug : bugList) {
                if(bug.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        }
    }
    public static void filterFeedbacksByStatus(String statusFilter, List<Feedback> feedbacksList, List<Feedback> filteredFeedbackList) {
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Feedback feedback : feedbacksList) {
                if(feedback.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredFeedbackList.add(feedback);
                }
            }
        }
    }
    public static void filterStoriesByStatus(String statusFilter, List<Story> storyList, List<Story> filteredStoryList) {
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Story story : storyList) {
                if(story.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredStoryList.add(story);
                }
            }
        }
    }

    /*TODO Ask Yuli to implement a getStatus() method in TaskImpl (Task) so we can use this generic method to filter
    any list by its status, no matter what is the status  */
    /* TODO We can try to use generic abstract method in Task to solve that the three task types have different
    return types in their getStatus() methods */


//    public static <E extends Task> List<E> filterTaskByStatus(String statusFilter, List<E> taskList, List<E> filteredList) {
//        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
//            for (E e : taskList) {
//                if(e.getStatus().toString().contains(statusFilter.toUpperCase())) {
//                    filteredList.add(e);
//                }
//            }
//        }
//        return filteredList;
//    }


//    public static void filterBugsByAssignee(String assigneeFilter, List<Bug> bugList, List<Bug> filteredBugList) {
//        if (!assigneeFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
//            for (Bug bug : bugList) {
//                if(bug.getAssignee().toString().toUpperCase().contains(assigneeFilter.toUpperCase())) {
//                    filteredBugList.add(bug);
//                }
//            }
//        }
//    }

    /* TODO Check if this filter works correctly. */
    public static <E extends Assignable> List<E> filterTasksByAssignee (String assigneeFilter, List<E> taskList, List<E> filteredList) {
        if (!assigneeFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
            for (E e : taskList) {
                if(e.getAssignee().toString().toUpperCase().contains(assigneeFilter.toUpperCase())) {
                    filteredList.add(e);
                }
            }
        }
        return filteredList;
    }

}
