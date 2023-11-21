package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AssignStoryCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
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

public class AssignStoryCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_MEMBER_NAME2 = "b".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_MEMBER_NAME3 = "c".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_STORY_TITLE = "a".repeat(StoryImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_STORY_DESCRIPTION = "a".repeat(StoryImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final StorySizeType VALID_STORY_SIZE = StorySizeType.LARGE;
    private static final StoryStatusType VALID_STORY_STATUS = StoryStatusType.IN_PROGRESS;
    private static final StoryPriorityType VALID_STORY_PRIORITY = StoryPriorityType.HIGH;
    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private AssignStoryCommand assignStoryCommand;
    private List<String> parameters;
    private Story story;
    private Member member;
    private Member member2;

    /*<-------Behavioural Method(s)------->*/
    @BeforeEach
    private void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        assignStoryCommand = new AssignStoryCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        member2 = teamManagementRepository.createMember(VALID_MEMBER_NAME2);
        story = teamManagementRepository.createStory(
                VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS, member);
    }

    @Test
    public void execute_Should_ThrowException_When_ListWithInvalidNumberOfParamsPassed() {
        /*Arrange*/
        parameters.add("1");

        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    assignStoryCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidStoryIdPassed() {
        /*Arrange*/
        parameters.add("100");
        parameters.add(VALID_MEMBER_NAME2);

        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> {
                    assignStoryCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidMemberNamePassed() {
        /*Arrange*/
        parameters.add("1");
        parameters.add(VALID_MEMBER_NAME3);

        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> {
                    assignStoryCommand.execute(parameters);
                });
    }
    @Test
    public void execute_Should_ThrowException_When_MemberAlreadyAssigned() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                VALID_MEMBER_NAME
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> assignStoryCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_executeSuccessfully_When_validParamsPassed() {
        /*Arrange*/
        parameters.add("1");
        parameters.add(VALID_MEMBER_NAME2);

        /*Act*/
        String resultFromSuccessfullAssignment = assignStoryCommand.execute(parameters);

        /*Assert*/
        Assertions.assertEquals(
                story.getActivityHistory().get(story.getActivityHistory().size() - 1), resultFromSuccessfullAssignment);
    }
}
