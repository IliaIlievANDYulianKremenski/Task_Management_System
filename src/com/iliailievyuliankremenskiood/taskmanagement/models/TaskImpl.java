package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {
    /*<-------Constant(s)------->*/
    public static final int MIN_TITLE_LENGTH = 10;
    public static final int MAX_TITLE_LENGTH = 100;
    private static final String INVALID_TITLE_LENGTH_MESSAGE = String.format("""
                    title's length should be between %d and %d characters!""",
            MIN_TITLE_LENGTH, MAX_TITLE_LENGTH);
    public static final int MIN_DESCRIPTION_LENGTH = 10;
    public static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final String INVALID_DESCRIPTION_LENGTH_MESSAGE = String.format("""
                    description's length should be between %d and %d characters!""",
            MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH);

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
    public String print() {
        return String.format("""
              Id: %d
              \tTitle: %s
              \tDescription: %s""",
                this.id, this.title, this.description);
    }


    protected boolean validateTitle(String title) {
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(String.format("%s' %s",
                    this.getClass().getName(), INVALID_TITLE_LENGTH_MESSAGE));
        }
        return true;
    }

    protected boolean validateDescription(String description) {
        if (description.length() < MIN_DESCRIPTION_LENGTH || description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException(String.format("%s' %s",
                    this.getClass().getName(), INVALID_DESCRIPTION_LENGTH_MESSAGE));
        }
        return true;
    }

    public void logCreation(String creationString) {
        this.activityHistory.add(produceCreationLogString(id, title, description));
    }

    protected String produceCreationLogString(int id, String title, String description) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        StringBuilder eventSb = new StringBuilder();
        eventSb.append(String.format("""
                        A %s with ID: '%d', Title: '%s', Description: '%s' was created on the following date and time: %s.""",
                this.getClass().getSimpleName(),
                id,
                title,
                description,
                dtf.format(now)));
        return eventSb.toString();
    }

    protected void logEvent(String attributeForWhichWeAreLogging,
                            String attributeOldContent,
                            String attributeNewContent) {
        StringBuilder eventSb = new StringBuilder();
        eventSb.append(String.format("The '%s' was changed from: '%s' to: '%s'. ",
                attributeForWhichWeAreLogging, attributeOldContent, attributeNewContent));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        eventSb.append(String.format("The change was done on the following date and time: %s.", dtf.format(now)));
        activityHistory.add(eventSb.toString());
    }

    /*TODO - make a test for this method.*/
    public void addCommentToTask(Comment commentToBeAddedToTheTask){
        this.comments.add(commentToBeAddedToTheTask);
    }
}