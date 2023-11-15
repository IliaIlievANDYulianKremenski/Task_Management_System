package com.iliailievyuliankremenskiood.taskmanagement.utils;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class FilterHelpers {

    /*<-------Constant(s)------->*/

    private static final String NO_BUGS_ERROR = "There are currently no Bugs.";
    private static final String NO_FEEDBACKS_ERROR = "There are currently no Feedbacks.";
    private static final String NO_STORIES_ERROR = "There are currently no Stories.";
    private static final String NO_TASKS_ERROR = "There are currently no elements of this task type.";


    /*<-------Behavioural Method(s)------->*/

    public static List<Bug> filterBugsByStatus(String statusFilter, List<Bug> bugList) {
        if (bugList.isEmpty()) {
            throw new IllegalArgumentException(NO_BUGS_ERROR);
        }
        List<Bug> filteredBugList = new ArrayList<>();
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Bug bug : bugList) {
                if(bug.getStatus().toString().contains(statusFilter)) {
                    filteredBugList.add(bug);
                }
            }
        } else {
            filteredBugList = new ArrayList<>(bugList);
        }
        return filteredBugList;
    }

    public static List<Feedback> filterFeedbacksByStatus(String statusFilter, List<Feedback> feedbacksList) {
        if (feedbacksList.isEmpty()) {
            throw new IllegalArgumentException(NO_FEEDBACKS_ERROR);
        }
        List<Feedback> filteredFeedbackList = new ArrayList<>();
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Feedback feedback : feedbacksList) {
                if(feedback.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredFeedbackList.add(feedback);
                }
            }
        } else {
            filteredFeedbackList = new ArrayList<>(filteredFeedbackList);
        }
        return filteredFeedbackList;
    }

    public static List<Story> filterStoriesByStatus(String statusFilter, List<Story> storyList) {
        if (storyList.isEmpty()) {
            throw new IllegalArgumentException(NO_STORIES_ERROR);
        }
        List<Story> filteredStoryList = new ArrayList<>();
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Story story : storyList) {
                if(story.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredStoryList.add(story);
                }
            }
        } else {
            filteredStoryList = new ArrayList<>(storyList);
        }
        return filteredStoryList;
    }

    /*TODO Ask Yuli to implement a getStatus() method in TaskImpl (Task) so we can use this generic method to filter
    any list by its status, no matter what is the status  */
    /* TODO We can try to use generic abstract method in Task to solve that the three task types have different
    return types in their getStatus() methods */


//    public static <E extends Task> List<E> filterTaskByStatus(String statusFilter, List<E> taskList) {
//         if (taskList.isEmpty()) {
//            throw new IllegalArgumentException(NO_TASKS_ERROR);
//        }
//          List<E> filteredList = new ArrayList<>();
//        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
//            for (E e : taskList) {
//                if(e.getStatus().toString().contains(statusFilter.toUpperCase())) {
//                    filteredList.add(e);
//                }
//            }
//        } else {
//          filteredList = new ArrayList<>(taskList);
//        return filteredList;
//    }

    public static List<Bug> filterBugsByAssignee(String assigneeFilter, List<Bug> bugList) {
        if (bugList.isEmpty()) {
            throw new IllegalArgumentException(NO_BUGS_ERROR);
        }
        List<Bug> filteredBugList = new ArrayList<>();
        if (!assigneeFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
            for (Bug bug : bugList) {
                if(bug.getAssignee().toString().toUpperCase().contains(assigneeFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        } else {
            filteredBugList = new ArrayList<>(bugList);
        }
        return filteredBugList;
    }

    /* TODO Check if this filter works correctly. */
    public static <E extends Assignable> List<E> filterTasksByAssignee (String assigneeFilter, List<E> taskList) {
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        }
        List<E> filteredList = new ArrayList<>();
        if (!assigneeFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
            for (E e : taskList) {
                if(e.getAssignee().toString().toUpperCase().contains(assigneeFilter.toUpperCase())) {
                    filteredList.add(e);
                }
            }
        } else {
            filteredList = new ArrayList<>(taskList);
        }
        return filteredList;
    }

}
