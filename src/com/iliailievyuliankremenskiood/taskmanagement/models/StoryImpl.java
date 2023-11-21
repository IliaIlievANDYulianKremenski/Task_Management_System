package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;

public class StoryImpl extends TaskImpl implements Story {
    private StoryPriorityType priorityType;
    private StorySizeType sizeType;
    private StoryStatusType statusType;
    private Member assignee;

    public StoryImpl(int id, String title, String description, StoryPriorityType priorityType,
                     StorySizeType sizeType, StoryStatusType statusType, Member assignee) {
        super(id, title, description);
        this.priorityType = priorityType;
        this.sizeType = sizeType;
        this.statusType = statusType;
        setAssignee(assignee);
        logCreation(produceCreationLogString(id, title, description));
    }

    @Override /*Story*/
    public StoryPriorityType getPriority() {
        return this.priorityType;
    }

    @Override /*Story*/
    public StorySizeType getSize() {
        return this.sizeType;
    }

    @Override /*Story*/
    public StoryStatusType getStatus() {
        return this.statusType;
    }

    @Override /*Story*/
    public Member getAssignee() {
        return this.assignee;
    }

    private void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    private void setPriorityType(StoryPriorityType priorityType) {
        this.priorityType = priorityType;
    }

    private void setSizeType(StorySizeType sizeType) {
        this.sizeType = sizeType;
    }

    private void setStatusType(StoryStatusType statusType) {
        this.statusType = statusType;
    }

    @Override /*TaskImpl - Printable*/
    public String print() {
        return String.format("""
                        --------------
                        Story:    
                            %s
                            Priority: %s
                            Size: %s
                            Status: %s
                            Assignee: %s
                        --------------""",
                super.print(),
                this.priorityType.toString(), this.sizeType.toString(),
                this.statusType.toString(), this.assignee.getName());
    }

    @Override /*TaskImpl*/
    protected String produceCreationLogString(int id, String title, String description) {
        StringBuilder eventSb = new StringBuilder();
        eventSb.append(super.produceCreationLogString(id, title, description));
        eventSb.append(String.format(" Priority: '%s', Size: '%s', Status: '%s', Assignee: '%s'.",
                this.priorityType.toString(),
                this.sizeType.toString(),
                this.statusType.toString(),
                this.assignee.getName()));
        return eventSb.toString();
    }

    @Override /*Story - Assignable*/
    public void changeAssignee(Member assignee) {
        String oldAssignee = this.getAssignee() == null ? "null" : this.getAssignee().getName();
        String newAssignee = assignee == null ? "null" : assignee.getName();
        setAssignee(assignee);
        logEvent("Story Assignee", oldAssignee, newAssignee);
    }

    @Override /*Story*/
    public void changeSize(StorySizeType size) {
        StorySizeType oldSize = this.getSize();
        setSizeType(size);
        logEvent("Story Size", oldSize.toString(), size.toString());
    }

    @Override /*Story*/
    public void changeStatus(StoryStatusType status) {
        StoryStatusType oldStatus = this.getStatus();
        setStatusType(status);
        logEvent("Story Status", oldStatus.toString(), status.toString());
    }

    @Override /*Story*/
    public void changePriority(StoryPriorityType priority) {
        StoryPriorityType oldPriority = this.getPriority();
        setPriorityType(priority);
        logEvent("Story Priority", oldPriority.toString(), priority.toString());
    }
}
