package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BigPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    /*<-------Constant(s)------->*/
    private static final int MIN_TITE_LENGHT = 10;
    private static final int MAX_TITE_LENGHT = 100;
    private static final String INVALID_TITLE_LENGTH_MESSAGE = String.format("""
                    Bug's title's length should be between %d and %d characters!""",
            MIN_TITE_LENGHT, MAX_TITE_LENGHT);
    private static final int MIN_DESCRIPTION_LENGHT = 10;
    private static final int MAX_DESCRIPTION_LENGHT = 500;
    private static final String INVALID_DESCRIPTION_LENGTH_MESSAGE = String.format("""
                    Bug's description's length should be between %d and %d characters!""",
            MIN_DESCRIPTION_LENGHT, MAX_DESCRIPTION_LENGHT);


    /*<-------Field(s)------->*/
    /*TODO:
       1 - should we provide the option to substitute the current stepsToReproduce with a brand new List?
       2 - should we provide the option to modify a step in the already existing list of steps?
       3 - should we provide the option to delete an existing step?*/
    private List<String> stepsToReproduce;

    /*TODO:
     *  1 - should we provide the option to change the priority to w/e priority regardless of the current one?*/
    private BigPriorityType priorityType;

    /*TODO:
     *  1 - should we provide the option to change the severity to w/e severity regardless of the current one?*/
    private BugSeverityType severityType;

    /*I have already implemented a method changeBugStatus(). It makes sense to at least be able to be able
     * to do this.*/
    private BugStatusType statusType;

    /*TODO:
     *  1 - should we provide the option to change the assignee of a Bug?*/
    private Member assignee;


    /*<-------Constructor(s)------->*/

    public BugImpl(int id, String title, String description, BigPriorityType priorityType,
                   BugSeverityType severityType, Member assignee) {
        super(id, title, description);
        this.stepsToReproduce = new ArrayList<>();
        setPriorityType(priorityType);
        setSeverityType(severityType);
        setBugStatusType(BugStatusType.ACTIVE);
        setAssignee(assignee);
    }


    /*<-------Getter(s)------->*/
    @Override /*Bug*/
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override /*Bug*/
    public BigPriorityType getPriority() {
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

    private void setPriorityType(BigPriorityType priorityType){
        this.priorityType = priorityType;
    }

    private void setSeverityType(BugSeverityType severityType){this.severityType = severityType;};

    private void setBugStatusType(BugStatusType bugStatusType){
        this.statusType = bugStatusType;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override /*TaskImpl*/
    protected boolean validateTitle(String title) {
        if (title.length() < MIN_TITE_LENGHT || title.length() > MAX_TITE_LENGHT) {
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH_MESSAGE);
        }
        return true;
    }

    @Override /*TaskImpl*/
    protected boolean validateDescription(String description) {
        if (description.length() < MIN_DESCRIPTION_LENGHT || description.length() > MAX_DESCRIPTION_LENGHT) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_LENGTH_MESSAGE);
        }
        return true;
    }


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
                            Id: %d
                            Title: %s
                            Description: %s
                            Priority: %s
                            Severity: %s
                            Status: %s
                            Assignee: %s
                        --------------""",
                this.getId(), this.getTitle(), this.getDescription(),
                this.priorityType, this.severityType, this.statusType, this.assignee.getName());
    }

    public void addStepsToReproduce(List<String> steps) {
        for (String step : steps) {
            stepsToReproduce.add(step);
        }
    }

    public void changeBugStatus() {
        if (this.statusType == BugStatusType.ACTIVE) {
            this.statusType = BugStatusType.DONE;
        } else if (this.statusType == BugStatusType.DONE) {
            this.statusType = BugStatusType.ACTIVE;
        }
    }

    public void changeAssignee(Member assignee) {
        setAssignee(assignee);
    }
}
