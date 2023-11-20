package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddCommentToStoryCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.CommentImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.TaskImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
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

public class AddCommentToStoryCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_STORY_TITLE = "a".repeat(TaskImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_STORY_DESCRIPTION = "a".repeat(TaskImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final StoryPriorityType VALID_STORY_PRIORITY = StoryPriorityType.HIGH;
    private static final StoryStatusType VALID_STORY_STATUS = StoryStatusType.IN_PROGRESS;
    private static final StorySizeType VALID_STORY_SIZE = StorySizeType.LARGE;
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_COMMENT_AUTHOR = "a".repeat(CommentImpl.AUTHOR_MIN_LEN + 1);
    private static final String INVALID_COMMENT_AUTHOR = "a".repeat(CommentImpl.AUTHOR_MIN_LEN -1);

    private static final String VALID_COMMENT_MESSAGE = "a".repeat(CommentImpl.MESSAGE_MIN_LEN + 1);
    private static final String INVALID_COMMENT_MESSAGE = "a".repeat(CommentImpl.MESSAGE_MIN_LEN - 1);


    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private AddCommentToStoryCommand addCommentToStoryCommand;
    private List<String> parameters;
    private Story story;
    private Member member;
    private Comment comment;


    /*<-------Behavioural Method(s)------->*/
    @BeforeEach
    public void setUp() {

        teamManagementRepository = new TeamManagementRepositoryImpl();
        addCommentToStoryCommand = new AddCommentToStoryCommand(teamManagementRepository);
        parameters = new ArrayList<>();

        member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        story = teamManagementRepository.createStory(
                VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS, member);
    }


    @Test
    public void execute_Should_ThrowException_When_ListWithInvalidNumberOfParamsPassed(){
        /*Arrange*/
        parameters.add("1");
        parameters.add("2");

        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {addCommentToStoryCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidStoryIDPassed(){
        /*Arrange*/
        parameters.add("100");
        parameters.add(VALID_COMMENT_AUTHOR);
        parameters.add(VALID_COMMENT_MESSAGE);


        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> {addCommentToStoryCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAuthorProvided(){
        /*Arrange*/
        parameters.add("1");
        parameters.add(INVALID_COMMENT_AUTHOR);
        parameters.add(VALID_COMMENT_MESSAGE);

        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> {addCommentToStoryCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidMessageProvided(){
        /*Arrange*/
        parameters.add("1");
        parameters.add(VALID_COMMENT_AUTHOR);
        parameters.add(INVALID_COMMENT_MESSAGE);

        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> {addCommentToStoryCommand.execute(parameters);});
    }

    @Test
    public void execute_Should_executeSuccessfully_When_ValidParamsPassed(){
        /*Arrange*/
        parameters.add("1");
        parameters.add(VALID_COMMENT_AUTHOR);
        parameters.add(VALID_COMMENT_MESSAGE);

        /*Act*/
        String resultFromSuccessfullCommentAddOperation = addCommentToStoryCommand.execute(parameters);

        /*Assert*/
        Assertions.assertEquals(
                story.getActivityHistory().get(story.getActivityHistory().size()-1),
                resultFromSuccessfullCommentAddOperation);
    }

}
