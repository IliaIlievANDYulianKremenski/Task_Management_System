package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.UnassignStoryCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.StoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnassignStoryCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN);
    private static final String VALID_STORY_TITLE = "a".repeat(StoryImpl.MIN_TITLE_LENGTH);
    private static final String VALID_STORY_DESCRIPTION = "a".repeat(StoryImpl.MIN_DESCRIPTION_LENGTH);

    private static final StoryPriorityType VALID_STORY_PRIORITY = StoryPriorityType.HIGH;
    private static final StorySizeType VALID_STORY_SIZE = StorySizeType.LARGE;
    private static final StoryStatusType VALID_STORY_STATUS = StoryStatusType.IN_PROGRESS;

    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private UnassignStoryCommand unassignStoryCommand;
    private List<String> parameters;

    private Story story;
    Member member;

    /*<-------Behavioural Method(s)------->*/
    @BeforeEach
    public void setUp(){
        teamManagementRepository = new TeamManagementRepositoryImpl();
        unassignStoryCommand = new UnassignStoryCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        member = new MemberImpl(VALID_MEMBER_NAME);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountOfParamsPassed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            unassignStoryCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_changeStoryAssignee_When_ValidParamPassed(){
        /*Arrange*/
        teamManagementRepository.createStory(
                VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        parameters.add("1");
        /*Act*/
        unassignStoryCommand.execute(parameters);

        /*Assert*/
        Assertions.assertNull(teamManagementRepository.findStoryById(1).getAssignee());
    }
}
