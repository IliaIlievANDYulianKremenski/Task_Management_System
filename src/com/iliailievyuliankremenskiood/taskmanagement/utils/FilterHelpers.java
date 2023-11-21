package com.iliailievyuliankremenskiood.taskmanagement.utils;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class FilterHelpers {

    public static List<Bug> filterBugsByStatus(String statusFilter, List<Bug> bugList, String message) {
        if (bugList.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        List<Bug> filteredBugList = new ArrayList<>();
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Bug bug : bugList) {
                if (bug.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        } else {
            filteredBugList = new ArrayList<>(bugList);
        }
        return filteredBugList;
    }

    public static List<Feedback> filterFeedbacksByStatus(String statusFilter, List<Feedback> feedbacksList, String message) {
        if (feedbacksList.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        List<Feedback> filteredFeedbackList = new ArrayList<>();
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Feedback feedback : feedbacksList) {
                if (feedback.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredFeedbackList.add(feedback);
                }
            }
        } else {
            filteredFeedbackList = new ArrayList<>(feedbacksList);
        }
        return filteredFeedbackList;
    }

    public static List<Story> filterStoriesByStatus(String statusFilter, List<Story> storyList, String message) {
        if (storyList.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        List<Story> filteredStoryList = new ArrayList<>();
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Story story : storyList) {
                if (story.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredStoryList.add(story);
                }
            }
        } else {
            filteredStoryList = new ArrayList<>(storyList);
        }
        return filteredStoryList;
    }

    public static List<Bug> filterBugsByAssignee(String assigneeFilter, List<Bug> bugList, String message) {
        if (bugList.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        List<Bug> filteredBugList = new ArrayList<>();
        if (!assigneeFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
            for (Bug bug : bugList) {
                if (bug.getAssignee() != null
                        && bug.getAssignee().getName().toUpperCase().contains(assigneeFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        } else {
            filteredBugList = new ArrayList<>(bugList);
        }
        return filteredBugList;
    }

    public static <E extends Assignable> List<E> filterTasksByAssignee(String assigneeFilter, List<E> taskList, String message) {
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        List<E> filteredList = new ArrayList<>();
        if (!assigneeFilter.equalsIgnoreCase("ALL_ASSIGNEES")) {
            for (E e : taskList) {
                if (e.getAssignee() != null
                        && e.getAssignee().getName().toUpperCase().contains(assigneeFilter.toUpperCase())) {
                    filteredList.add(e);
                }
            }
        } else {
            filteredList = new ArrayList<>(taskList);
        }
        return filteredList;
    }
}
