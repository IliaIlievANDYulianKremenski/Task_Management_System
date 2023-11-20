package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewStoryCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.StoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewStoryCommandTests {
    /*<-------Constant(s)------->*/

    private static final String VALID_STORY_TITLE = "a".repeat(StoryImpl.MIN_TITLE_LENGTH + 1 );
    private static final String VALID_STORY_DESCRIPTION = "a".repeat(StoryImpl.MIN_DESCRIPTION_LENGTH + 1 );
    private static final StoryPriorityType VALID_STORY_PRIORITY = StoryPriorityType.HIGH;
    private static final StorySizeType VALID_STORY_SIZE = StorySizeType.LARGE;
    private static final StoryStatusType VALID_STORY_STATUS  = StoryStatusType.IN_PROGRESS;
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);

    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private CreateNewStoryCommand createNewStoryCommand;
    private List<String> parameters;
    private Member member;
    /*<-------Behavioural Method(s)------->*/

    @BeforeEach
    private void setUp(){
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewStoryCommand = new CreateNewStoryCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        member = teamManagementRepository.createMember(VALID_MEMBER_NAME);

    };

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountOfParamsPassed(){
        /*Arrange*/
        parameters.add("test");

        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->{
                    createNewStoryCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_createAValidNewStory_When_ValidParamPassed(){
        /*Arrange*/
        parameters.add(VALID_STORY_TITLE);
        parameters.add(VALID_STORY_DESCRIPTION);
        parameters.add(VALID_STORY_PRIORITY.toString());
        parameters.add(VALID_STORY_SIZE.toString());
        parameters.add(VALID_STORY_STATUS.toString());
        parameters.add(VALID_MEMBER_NAME);

        /*Act*/
        String resultFromSuccessfullStoryCreation = createNewStoryCommand.execute(parameters);

        /*Assert*/
        Assertions.assertEquals(
                teamManagementRepository.findStoryById(1).getActivityHistory().get(
                        teamManagementRepository.findStoryById(1).getActivityHistory().size()-1),
                resultFromSuccessfullStoryCreation);
    }
}
