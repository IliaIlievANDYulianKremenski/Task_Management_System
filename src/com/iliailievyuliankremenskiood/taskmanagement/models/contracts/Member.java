package com.iliailievyuliankremenskiood.taskmanagement.models.contracts;

import java.util.List;

public interface Member extends Loggable {
    String getName();

    List<Task> getMemberTasks();

    List<String> getActivityHistory();

    void assignTask(Task task);

    void unassignTask(Task task);

    String viewTasksInfo();

    String getActivityInfo();
}
