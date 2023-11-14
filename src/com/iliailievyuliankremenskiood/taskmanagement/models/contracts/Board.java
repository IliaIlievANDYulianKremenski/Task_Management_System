package com.iliailievyuliankremenskiood.taskmanagement.models.contracts;

import java.util.List;

public interface Board extends Loggable {
    String getName();
    List<Task> getTasks();
    List<String> getActivityHistory();
    void createTaskInBoard(Task task);
    void removeTaskFromBoard(Task task);

}