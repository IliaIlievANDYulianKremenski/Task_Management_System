package com.iliailievyuliankremenskiood.taskmanagement.models.contracts;

import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;

public interface Feedback extends Task{
    int getRating();

    FeedbackStatusType getStatus();

    void changeRating(int rating);

    void changeStatus(FeedbackStatusType status);
}
