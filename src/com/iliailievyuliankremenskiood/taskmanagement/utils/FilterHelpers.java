package com.iliailievyuliankremenskiood.taskmanagement.utils;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Assignable;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;

import java.util.List;

public class FilterHelpers {

    public static void filterBugsByStatus(String statusFilter, List<Bug> bugList, List<Bug> filteredBugList) {
        if (!statusFilter.equalsIgnoreCase("ALL_STATUSES")) {
            for (Bug bug : bugList) {
                if(bug.getStatus().toString().contains(statusFilter.toUpperCase())) {
                    filteredBugList.add(bug);
                }
            }
        }
    }

    /*TODO Ask Yuli to implement a getStatus() method in TaskImpl (Task) so we can use this generic method to filter
    any list by its status, no matter what is the status  */


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
