package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {
    /*<-------Constant(s)------->*/


    /*<-------Field(s)------->*/
    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<String> activityHistory;


    /*<-------Constructor(s)------->*/
    public TaskImpl(int id, String title, String description) {
        setId(id);
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        logCreation();
    }


    /*<-------Getter(s)------->*/
    @Override /*Task*/
    public int getId() {
        return this.id;
    }

    @Override /*Task*/
    public String getTitle() {
        return this.title;
    }

    @Override /*Task*/
    public String getDescription() {
        return this.description;
    }

    @Override /*Task*/
    public List<Comment> getComments() {
        return new ArrayList<>(this.comments);
    }

    @Override /*Task*/
    public List<String> getActivityHistory() {
        return new ArrayList<>(this.activityHistory);
    }


    /*<-------Setter(s)------->*/
    private void setId(int id) {
        this.id = id;
    }


    private void setTitle(String title) {
        if (validateTitle(title)) {
            this.title = title;
        }
    }

    private void setDescription(String description) {
        if (validateDescription(description)) {
            this.description = description;
        }
    }


    /*<-------Behavioural Method(s)------->*/
    @Override /*Printable*/
    abstract public String print();

    /*Different types of tasks will have different validations*/
    abstract protected boolean validateTitle(String title);

    /*Different types of tasks will have different validations*/
    abstract protected boolean validateDescription(String description);

    private void logCreation() {
        this.activityHistory.add(produceCreationLogString(id, title, description));
    }
    protected String produceCreationLogString(int id, String title, String description){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        StringBuilder eventSb = new StringBuilder();
        eventSb.append(String.format(
                """
                A %s with ID: '%d', Title: '%s', Description: '%s' was created on the following date and time: %s.""",
                this.getClass().getName(),
                id,
                title,
                description,
                dtf.format(now)));
        return eventSb.toString();
    }

    private void logEvent(String attributeForWhichWeAreLogging, String attributeContent) {
        StringBuilder eventSb = new StringBuilder();
        eventSb.append(String.format("The %s was set to: %s. ", attributeForWhichWeAreLogging, attributeContent));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        eventSb.append(String.format("The change was done on the following date and time: %s.", dtf.format(now)));
        activityHistory.add(eventSb.toString());
    }
}
