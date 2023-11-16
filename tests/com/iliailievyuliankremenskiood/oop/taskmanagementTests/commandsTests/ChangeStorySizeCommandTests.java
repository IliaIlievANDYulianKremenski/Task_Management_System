package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ChangeStorySizeCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
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

public class ChangeStorySizeCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_STORY_TITLE = "a".repeat(StoryImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_STORY_DESCRIPTION = "a".repeat(StoryImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final StoryPriorityType VALID_STORY_PRIORITY = StoryPriorityType.HIGH;
    private static final StorySizeType VALID_STORY_SIZE = StorySizeType.LARGE;
    private static final StorySizeType VALID_STORY_SIZE2 = StorySizeType.MEDIUM;
    private static final StoryStatusType VALID_STORY_STATUS = StoryStatusType.IN_PROGRESS;
    private static final String INVALID_STORY_SIZE = "SOMETHING_INVALID";


    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private ChangeStorySizeCommand changeStorySizeCommand;
    private List<String> parameters;
    private Story story;
    private Member member;


    /*<-------Behavioural Method(s)------->*/

    @BeforeEach
    private void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        changeStorySizeCommand = new ChangeStorySizeCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        member = new MemberImpl(VALID_MEMBER_NAME);
        story = teamManagementRepository.createStory(
                VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member
        );

    }

    @Test
    public void execute_Should_ThrowException_When_ListWithInvalidNumberOfParamsPassed() {
        /*Arrange*/
        parameters.add("test");

        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    changeStorySizeCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidStoryIdPassed() {
        /*Arrange*/
        parameters.add("100");
        parameters.add(VALID_STORY_SIZE.toString());

        /*Act, Assert*/
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> {
                    changeStorySizeCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidSizePassed() {
        /*Arrange*/
        parameters.add("1");
        parameters.add(INVALID_STORY_SIZE.toString());

        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    changeStorySizeCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_executeSuccessfully_When_validParamsPassed() {
        /*Arrange*/
        parameters.add("1");
        parameters.add(VALID_STORY_SIZE2.toString());

        /*Act*/
        String resultFromSuccessfullStorySizeChange = changeStorySizeCommand.execute(parameters);

        /*Assert*/
        Assertions.assertEquals(
                story.getActivityHistory().get(story.getActivityHistory().size() - 1),
                resultFromSuccessfullStorySizeChange
        );
    }
}
