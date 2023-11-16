package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    /*<-------Constant(s)------->*/


    /*<-------Field(s)------->*/
    /*TODO:
       1 - should we provide the option to substitute the current stepsToReproduce with a brand new List?
       2 - should we provide the option to modify a step in the already existing list of steps?
       3 - should we provide the option to delete an existing step?*/
    private final List<String> stepsToReproduce;

    private BugPriorityType priorityType;

    private BugSeverityType severityType;
    
    private BugStatusType statusType;

    /* ✏️ TODO ✏️ Ask the trainers if it is necessary the task to have an assignee, in other words can we use null as a default value for Member assignee or
    it is mandatory to have assignee when creating a new task?*/
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
        logCreation(produceCreationLogString(id, title, description));
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
        String oldSteps = this.getStepsToReproduce().toString();
        for (String step : steps) {
            stepsToReproduce.add(step);
        }
        logEvent("Bug Steps_To_Reproduce", oldSteps, steps.toString());
    }

    @Override /*Bug*/
    public void changeBugStatus() {
        if (this.statusType == BugStatusType.ACTIVE) {
            this.statusType = BugStatusType.DONE;
            logEvent("Bug Status",
                    BugStatusType.ACTIVE.toString(),BugStatusType.DONE.toString());
        } else if (this.statusType == BugStatusType.DONE) {
            this.statusType = BugStatusType.ACTIVE;
            logEvent("Bug Status",
                    BugStatusType.DONE.toString(),BugStatusType.ACTIVE.toString());
        }
    }

    @Override /*Bug*/
    public void changeBugPriority(BugPriorityType bugPriority) {
        BugPriorityType oldPriority = this.getPriority();
        setPriorityType(bugPriority);
        logEvent("Bug Priority", oldPriority.toString(), bugPriority.toString());
    }

    @Override /*Bug*/
    public void changeBugSeverity(BugSeverityType bugSeverityType) {
        BugSeverityType oldSeverity = this.getSeverity();
        setSeverityType(bugSeverityType);
        logEvent("Bug Severity", oldSeverity.toString(),bugSeverityType.toString());
    }


    /*TODO Implement if statement in changeAssignee() method to check whether the parameter is null ot not.*/
    @Override /*Bug*/
    public void changeAssignee(Member assignee) {
        Member oldAssignee = this.getAssignee();
        setAssignee(assignee);
        logEvent("Bug Assignee", oldAssignee.getName(), assignee.getName());
    }


}
