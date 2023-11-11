package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;

public interface Story extends Assignable {
    StorySizeType getSize();
    StoryStatusType getStatus();
    StoryPriorityType getPriority();
}
