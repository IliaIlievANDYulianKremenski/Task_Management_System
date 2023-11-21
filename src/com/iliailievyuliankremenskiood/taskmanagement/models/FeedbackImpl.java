package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private static final String INVALID_RATING_MESSAGE = "The rating should be between 1 and 10.";
    public static final int FEEDBACK_MIN_RATING = 1;
    public static final int FEEDBACK_MAX_RATING = 10;
    private int rating;
    private FeedbackStatusType status;

    public FeedbackImpl(int id, String title, String description, int rating, FeedbackStatusType status) {
        super(id, title, description);
        setRating(rating);
        setStatus(status);
        logCreation(produceCreationLogString(id, title, description));
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    public FeedbackStatusType getStatus() {
        return this.status;
    }

    private void setRating(int rating) {
        if (validateRating(rating)) {
            this.rating = rating;
        }
    }

    private void setStatus(FeedbackStatusType status) {
        this.status = status;
    }

    @Override
    public String print() {
        return String.format("""
                        --------------
                        Feedback:    
                            %s
                            Rating: %d
                            Status: %s
                        --------------""",
                super.print(), this.rating, this.status);
    }

    @Override /*TaskImpl*/
    protected String produceCreationLogString(int id, String title, String description) {
        StringBuilder eventSb = new StringBuilder();

        eventSb.append(super.produceCreationLogString(id, title, description));
        eventSb.append(String.format(" Rating: '%d', Status: '%s'.", this.rating, this.status.toString()));

        return eventSb.toString();
    }

    @Override /*Feedback*/
    public void changeRating(int rating) {
        int oldRating = this.rating;
        setRating(rating);
        logEvent("Feedback rating",
                String.format("%d", oldRating),
                String.format("%d", rating));
    }

    @Override /*Feedback*/
    public void changeStatus(FeedbackStatusType status) {
        FeedbackStatusType oldStatus = this.getStatus();
        setStatus(status);
        logEvent("Feedback status",
                String.format("%s", oldStatus.toString()),
                String.format("%s", status.toString()));
    }

    public boolean validateRating(int rating) {
        if (rating < FEEDBACK_MIN_RATING || rating > FEEDBACK_MAX_RATING) {
            throw new IllegalArgumentException(INVALID_RATING_MESSAGE);
        }
        return true;
    }
}
