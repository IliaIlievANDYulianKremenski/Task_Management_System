package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    /*<-------Constant(s)------->*/


    /*<-------Field(s)------->*/
    /*TODO:
       1 - should we provide the option to substitute the current stepsToReproduce with a brand new List?
       2 - should we provide the option to modify a step in the already existing list of steps?
       3 - should we provide the option to delete an existing step?*/
    private List<String> stepsToReproduce;

    private BugPriorityType priorityType;

    private BugSeverityType severityType;
    
    private BugStatusType statusType;

    private Member assignee;


    /*<-------Constructor(s)------->*/

    public BugImpl(int id, String title, String description, BugPriorityType priorityType,
                   BugSeverityType severityType, Member assignee) {
        super(id, title, description);
        this.stepsToReproduce = new ArrayList<>();
        setPriorityType(priorityType);
        setSeverityType(severityType);
        setBugStatusType(BugStatusType.ACTIVE);
        setAssignee(assignee);
        logCreation();
    }


    /*<-------Getter(s)------->*/
    @Override /*Bug*/
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override /*Bug*/
    public BugPriorityType getPriority() {
        return this.priorityType;
    }

    @Override /*Bug*/
    public BugSeverityType getSeverity() {
        return this.severityType;
    }

    @Override /*Bug*/
    public BugStatusType getStatus() {
        return this.statusType;
    }

    @Override /*Bug*/
    public Member getAssignee() {
        return this.assignee;
    }


    /*<-------Setter(s)------->*/
    private void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    private void setPriorityType(BugPriorityType priorityType) {
        this.priorityType = priorityType;
    }

    private void setSeverityType(BugSeverityType severityType) {
        this.severityType = severityType;
    }

    ;

    private void setBugStatusType(BugStatusType bugStatusType) {
        this.statusType = bugStatusType;
    }


    /*<-------Behavioural Method(s)------->*/


    @Override /*TaskImpl*/
    protected String produceCreationLogString(int id, String title, String description) {
        StringBuilder eventSb = new StringBuilder();

        eventSb.append(super.produceCreationLogString(id, title, description));

        eventSb.append(String.format(" Priority: '%s', Severity: '%s', Status: '%s', Assignee: '%s'.",
                this.priorityType.toString(),
                this.severityType.toString(),
                this.statusType.toString(),
                this.assignee.getName()));

        return eventSb.toString();
    }

    @Override /*TaskImpl*/
    public String print() {
        return String.format("""
                        --------------
                        Bug:
                            %s
                            Priority: %s
                            Severity: %s
                            Status: %s
                            Assignee: %s
                        --------------""",
                super.print(),
                this.priorityType, this.severityType, this.statusType, this.assignee.getName());
    }

    public void addStepsToReproduce(List<String> steps) {
        for (String step : steps) {
            stepsToReproduce.add(step);
        }
    }

    @Override /*Bug*/
    public void changeBugStatus() {
        if (this.statusType == BugStatusType.ACTIVE) {
            this.statusType = BugStatusType.DONE;
        } else if (this.statusType == BugStatusType.DONE) {
            this.statusType = BugStatusType.ACTIVE;
        }
    }

    @Override /*Bug*/
    public void changeBugPriority(BugPriorityType bugPriority) {
        setPriorityType(bugPriority);
    }

    @Override /*Bug*/
    public void changeBugSeverity(BugSeverityType bugSeverityType) {
        setSeverityType(bugSeverityType);
    }

    @Override /*Bug*/
    public void changeAssignee(Member assignee) {
        setAssignee(assignee);
    }


}
