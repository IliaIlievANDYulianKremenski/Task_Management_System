package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

public interface Assignable {
    Member getAssignee();

    void changeAssignee(Member assignee);
}
