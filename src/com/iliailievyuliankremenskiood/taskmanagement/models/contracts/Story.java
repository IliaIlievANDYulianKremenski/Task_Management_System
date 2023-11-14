package com.iliailievyuliankremenskiood.taskmanagement.models.contracts;

import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;

public interface Story extends Task, Assignable {
    StorySizeType getSize();

    StoryStatusType getStatus();

    StoryPriorityType getPriority();

    void changeSize(StorySizeType size);

    void changeStatus(StoryStatusType status);

    void changePriority(StoryPriorityType priority);
}
