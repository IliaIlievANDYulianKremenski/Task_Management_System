package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StoryImpl extends TaskImpl implements Story {
    /*<-------Constant(s)------->*/
    private static final int MIN_TITE_LENGHT = 10;
    private static final int MAX_TITE_LENGHT = 100;
    private static final String INVALID_TITLE_LENGTH_MESSAGE = String.format("""
                    Story's title's length should be between %d and %d characters!""",
            MIN_TITE_LENGHT, MAX_TITE_LENGHT);
    private static final int MIN_DESCRIPTION_LENGHT = 10;
    private static final int MAX_DESCRIPTION_LENGHT = 500;
    private static final String INVALID_DESCRIPTION_LENGTH_MESSAGE = String.format("""
                    Story's description's length should be between %d and %d characters!""",
            MIN_DESCRIPTION_LENGHT, MAX_DESCRIPTION_LENGHT);


    /*<-------Field(s)------->*/
    /*TODO:
     *  - should we be able to change the priority?*/
    private StoryPriorityType priorityType;

    private StorySizeType sizeType;

    /*TODO:
     *  - should we be able to change the status? It seems as something natural.*/
    private StoryStatusType statusType;

    /*TODO:
     *  - should we be able to change the assignee?*/
    private Member assignee;


    /*<-------Constructor(s)------->*/
    public StoryImpl(int id, String title, String description, StoryPriorityType priorityType,
                     StorySizeType sizeType, StoryStatusType statusType, Member assignee) {
        super(id, title, description);
        this.priorityType = priorityType;
        this.sizeType = sizeType;
        this.statusType = statusType;
        setAssignee(assignee);
    }


    /*<-------Getter(s)------->*/
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


    /*<-------Setter(s)------->*/
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


    /*<-------Behavioural Method(s)------->*/
    @Override /*TaskImpl - Printable*/
    public String print() {
        return String.format("""
                --------------
                Story:
                    Id: %d
                    Title: %s
                    Description: %s
                    Priority: %s
                    Size: %s
                    Status: %s
                    Assignee: %s
                --------------""",
                this.getId(), this.getTitle(), this.getDescription(),
                this.priorityType.toString(), this.sizeType.toString(),
                this.statusType.toString(), this.assignee.getName());
    }

    @Override
    protected boolean validateTitle(String title) {
        if (title.length() < MIN_TITE_LENGHT || title.length() > MAX_TITE_LENGHT) {
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH_MESSAGE);
        }
        return true;
    }

    @Override
    protected boolean validateDescription(String description) {
        if (description.length() < MIN_DESCRIPTION_LENGHT || description.length() > MAX_DESCRIPTION_LENGHT) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_LENGTH_MESSAGE);
        }
        return true;
    }

    @Override /*TaskImpl*/
    protected String produceCreationLogString(int id, String title, String description){
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
        setAssignee(assignee);
    }

}
