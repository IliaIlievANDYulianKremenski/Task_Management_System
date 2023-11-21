package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    /*TODO - when we are 100% ready with the functionality of the program
        we can color the error messages we send to the user and make it more pleasant to watch.*/
    private final List<String> stepsToReproduce;
    private BugPriorityType priorityType;
    private BugSeverityType severityType;
    private BugStatusType statusType;
    private Member assignee;

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

    @Override /*TaskImpl*/
    protected String produceCreationLogString(int id, String title, String description) {
        StringBuilder eventSb = new StringBuilder();
        eventSb.append(super.produceCreationLogString(id, title, description));
        eventSb.append(String.format("\nPriority: '%s'\nSeverity: '%s'\nStatus: '%s'\nAssignee: '%s'.",
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

    public void addStepToReproduce(String step) {
        String oldSteps = this.getStepsToReproduce().toString();
        stepsToReproduce.add(step);
        logEvent("Bug Steps_To_Reproduce", oldSteps, step.toString());
    }

    @Override /*Bug*/
    public void changeBugStatus() {
        if (this.statusType == BugStatusType.ACTIVE) {
            this.statusType = BugStatusType.DONE;
            logEvent("Bug Status",
                    BugStatusType.ACTIVE.toString(), BugStatusType.DONE.toString());
        } else if (this.statusType == BugStatusType.DONE) {
            this.statusType = BugStatusType.ACTIVE;
            logEvent("Bug Status",
                    BugStatusType.DONE.toString(), BugStatusType.ACTIVE.toString());
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
        logEvent("Bug Severity", oldSeverity.toString(), bugSeverityType.toString());
    }

    @Override /*Bug*/
    public void changeAssignee(Member assignee) {
        String oldAssignee = this.getAssignee() == null ? "null" : this.getAssignee().getName();
        String newAssignee = assignee == null ? "null" : assignee.getName();
        setAssignee(assignee);
        logEvent("Bug Assignee", oldAssignee, newAssignee);
    }
}
