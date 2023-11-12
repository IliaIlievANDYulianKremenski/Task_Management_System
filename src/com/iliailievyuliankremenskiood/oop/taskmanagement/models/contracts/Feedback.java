package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;

public interface Feedback extends Task{
    int getRating();

    FeedbackStatusType getStatus();

    void changeRating(int rating);

    void changeStatus(FeedbackStatusType status);
}
