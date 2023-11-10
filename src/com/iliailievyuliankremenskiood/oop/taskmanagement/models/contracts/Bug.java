package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

public interface Bug {
    void getStepsToReproduce();
    void getPriority();
    void getSeverity();
    void getStatus();
    void getAssignee();
}
