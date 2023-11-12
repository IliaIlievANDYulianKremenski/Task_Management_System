package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BigPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;

import java.util.List;

public interface Bug extends Task, Assignable {
    List<String> getStepsToReproduce();

    BigPriorityType getPriority();

    BugSeverityType getSeverity();

    BugStatusType getStatus();

    void changeBugPriority(BigPriorityType bugPriority);

    void changeBugSeverity(BugSeverityType bugSeverityType);

    void changeBugStatus();

}
