package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import java.util.List;

public interface Board {
    String getName();
    List<Task> getTasks();
    List<String> getActivityHistory();
    void createTaskInBoard(Task task);
    void removeTaskFromBoard(Task task);

}
