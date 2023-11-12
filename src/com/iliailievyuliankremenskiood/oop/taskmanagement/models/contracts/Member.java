package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import java.util.List;

public interface Member {
    String getName();
    List<Task> getTasks();
    List<String> getActivityHistory();
    void assignTask(Task task);
    void unassignTask(Task task);
    void viewTasksInfo();
    void viewActivityInfo();


}
