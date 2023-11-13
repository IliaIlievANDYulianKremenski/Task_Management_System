package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import java.util.List;

public interface Task extends Printable, Loggable {
    int getId();
    String getTitle();
    String getDescription();
    List<Comment> getComments();
    List<String> getActivityHistory();

}
