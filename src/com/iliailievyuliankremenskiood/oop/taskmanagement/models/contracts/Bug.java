package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;

import java.util.List;

public interface Bug extends Task, Assignable {
    List<String> getStepsToReproduce();

    BugPriorityType getPriority();

    BugSeverityType getSeverity();

    BugStatusType getStatus();

    void changeBugPriority(BugPriorityType bugPriority);

    void changeBugSeverity(BugSeverityType bugSeverityType);

    void changeBugStatus();
    void addStepsToReproduce(List<String> steps);

}
