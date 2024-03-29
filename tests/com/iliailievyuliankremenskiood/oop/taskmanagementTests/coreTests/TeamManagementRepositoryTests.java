package com.iliailievyuliankremenskiood.oop.taskmanagementTests.coreTests;

import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.NameAlreadyExistException;
import com.iliailievyuliankremenskiood.taskmanagement.models.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamManagementRepositoryTests {

    public static final String VALID_MEMBER_NAME = "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN);
    public static final String VALID_BOARD_NAME = "A".repeat(BoardImpl.BOARD_NAME_MIN_LEN);
    public static final String VALID_TEAM_NAME = "A".repeat(TeamImpl.TEAM_NAME_MIN_LEN);
    public static final int VALID_TASK_ID = 1;
    public static final String VALID_BUG_TITLE = "A".repeat(BugImpl.MIN_TITLE_LENGTH);
    public static final String VALID_BUG_DESCRIPTION = "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH);
    public static final String VALID_COMMENT_AUTHOR = "A".repeat(CommentImpl.AUTHOR_MIN_LEN);
    public static final String VALID_COMMENT_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MIN_LEN);
    public static final String VALID_FEEDBACK_TITLE = "A".repeat(FeedbackImpl.MIN_TITLE_LENGTH);
    public static final String VALID_FEEDBACK_DESCRIPTION = "A".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH);
    public static final int FEEDBACK_RATING = 1;
    public static final String VALID_STORY_TITLE = "A".repeat(StoryImpl.MIN_TITLE_LENGTH);
    public static final String VALID_STORY_DESCRIPTION = "A".repeat(StoryImpl.MIN_DESCRIPTION_LENGTH);
    private TeamManagementRepository teamManagementRepository;

    @BeforeEach
    public void setTeamManagementRepository() {

        teamManagementRepository = new TeamManagementRepositoryImpl();

    }

    @Test
    public void constructor_Should_InitializeAllCollections() {
        Assertions.assertEquals(0, teamManagementRepository.getMembers().size());
        Assertions.assertEquals(0, teamManagementRepository.getTasks().size());
        Assertions.assertEquals(0, teamManagementRepository.getBugs().size());
        Assertions.assertEquals(0, teamManagementRepository.getFeedbacks().size());
        Assertions.assertEquals(0, teamManagementRepository.getStories().size());
        Assertions.assertEquals(0, teamManagementRepository.getTeams().size());
        Assertions.assertEquals(0, teamManagementRepository.getComments().size());
    }

    @Test
    public void getMember_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getMembers(),
                teamManagementRepository.getMembers());

    }

    @Test
    public void getTask_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getTasks(),
                teamManagementRepository.getTasks());

    }

    @Test
    public void getBugs_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getBugs(),
                teamManagementRepository.getBugs());

    }

    @Test
    public void getStories_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getStories(),
                teamManagementRepository.getStories());

    }

    @Test
    public void getFeedbacks_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getFeedbacks(),
                teamManagementRepository.getFeedbacks());

    }

    @Test
    public void getTeams_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getTeams(),
                teamManagementRepository.getTeams());

    }

    @Test
    public void getComments_Should_ReturnCopyOfCollection() {
        Assertions.assertNotSame(teamManagementRepository.getComments(),
                teamManagementRepository.getComments());

    }

    @Test
    public void findMemberByName_Should_ThrowException_When_MemberDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findMemberByName(VALID_MEMBER_NAME));

    }

    @Test
    public void findMemberByName_Should_ReturnMember_When_MemberExists() {
        Member member = createValidMember();
        Member member2 = teamManagementRepository.findMemberByName(VALID_MEMBER_NAME);
        Assertions.assertSame(member, member2);
    }

    @Test
    public void findBoardByName_Should_ThrowException_When_BoardDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findBoardByName(VALID_BOARD_NAME, VALID_TEAM_NAME));
    }

    @Test
    public void findBoardByName_Should_ReturnBoard_When_BoardExists() {
        Team team = createValidTeam();
        Board board = createValidBoard();
        Board board2 = teamManagementRepository.findBoardByName(VALID_BOARD_NAME, VALID_TEAM_NAME);
        Assertions.assertSame(board, board2);
    }

    @Test
    public void findTeamByName_Should_ThrowException_When_TeamDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findTeamByName(VALID_TEAM_NAME));
    }

    @Test
    public void findTeamByName_Should_ReturnBoard_When_TeamExists() {
        Team team = createValidTeam();
        Team team2 = teamManagementRepository.findTeamByName(VALID_TEAM_NAME);
        Assertions.assertSame(team, team2);
    }

    @Test
    public void findTaskById_Should_ThrowException_When_TaskDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findTaskById(VALID_TASK_ID));
    }

    @Test
    public void findTaskById_Should_ReturnVehicle_When_TaskExists() {
        Task bug = createValidBug();
        Task bug2 = teamManagementRepository.findTaskById(VALID_TASK_ID);
        assertAll(
                () -> assertSame(bug, bug2),
                () -> assertEquals(bug.getId(), bug2.getId()),
                () -> assertEquals(bug.getTitle(), bug2.getTitle()),
                () -> assertEquals(bug.getDescription(), bug2.getDescription())
        );
    }

    @Test
    public void findBugById_Should_ThrowException_When_BugDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findBugById(VALID_TASK_ID));
    }

    @Test
    public void findBugById_Should_ReturnVehicle_When_BugExists() {
        Bug bug = createValidBug();
        Bug bug2 = teamManagementRepository.findBugById(VALID_TASK_ID);
        assertAll(
                () -> assertSame(bug, bug2),
                () -> assertEquals(bug.getId(), bug2.getId()),
                () -> assertEquals(bug.getTitle(), bug2.getTitle()),
                () -> assertEquals(bug.getDescription(), bug2.getDescription()),
                () -> assertEquals(bug.getPriority(), bug2.getPriority()),
                () -> assertEquals(bug.getAssignee(), bug2.getAssignee())
        );
    }

    @Test
    public void findStoryById_Should_ThrowException_When_StoryDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findStoryById(VALID_TASK_ID));
    }

    @Test
    public void findStoryById_Should_ReturnVehicle_When_StoryExists() {
        Story story = createValidStory();
        Story story2 = teamManagementRepository.findStoryById(VALID_TASK_ID);
        assertAll(
                () -> assertEquals(story.getId(), story2.getId()),
                () -> assertEquals(story.getTitle(), story2.getTitle()),
                () -> assertEquals(story.getDescription(), story2.getDescription()),
                () -> assertEquals(story.getPriority(), story2.getPriority()),
                () -> assertEquals(story.getSize(), story2.getSize()),
                () -> assertEquals(story.getStatus(), story2.getStatus()),
                () -> assertEquals(story.getAssignee(), story2.getAssignee())
        );
    }

    @Test
    public void findFeedbackById_Should_ThrowException_When_FeedbackDoesNotExist() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> teamManagementRepository.findFeedbackById(VALID_TASK_ID));

    }

    @Test
    public void findFeedbackById_Should_ReturnVehicle_When_FeedbackExists() {
        Feedback feedback = createValidFeedback();
        Feedback feedback2 = teamManagementRepository.findFeedbackById(VALID_TASK_ID);
        assertAll(
                () -> assertEquals(feedback.getId(), feedback2.getId()),
                () -> assertEquals(feedback.getTitle(), feedback2.getTitle()),
                () -> assertEquals(feedback.getDescription(), feedback2.getDescription()),
                () -> assertEquals(feedback.getRating(), feedback2.getRating()),
                () -> assertEquals(feedback.getStatus(), feedback2.getStatus())
        );
    }

    @Test
    public void createMember_Should_AddMemberToList() {
        Member member = createValidMember();
        Assertions.assertEquals(1, teamManagementRepository.getMembers().size());
    }

    @Test
    public void createMember_Should_ThrowException_When_MemberNameExists() {
        Member member = createValidMember();
        Assertions.assertThrows(
                NameAlreadyExistException.class,
                () -> createValidMember()
        );
    }

    @Test
    public void createTeam_Should_AddTeamToList() {
        Team team = createValidTeam();
        Assertions.assertEquals(1, teamManagementRepository.getTeams().size());
    }

    @Test
    public void createTeam_Should_ThrowException_When_TeamNameExists() {
        Team team = createValidTeam();
        Assertions.assertThrows(
                NameAlreadyExistException.class,
                () -> createValidTeam()
        );
    }

    @Test
    public void createBoard_Should_AddBoardToList() {
        Team team = createValidTeam();
        Board board = createValidBoard();
        Assertions.assertEquals(
                1,
                teamManagementRepository.getTeams().get(0).getTeamBoards().size()
        );
        Assertions.assertEquals(1, team.getTeamBoards().size());
    }

    @Test
    public void createBoard_Should_ThrowException_When_BoardNameExists() {
        Team team = createValidTeam();
        Board board = createValidBoard();
        Assertions.assertThrows(
                NameAlreadyExistException.class,
                () -> createValidBoard()
        );
    }

    @Test
    public void createComment_Should_AddCommentToList() {
        createValidMember();
        Comment comment = createValidComment();
        Assertions.assertEquals(1, teamManagementRepository.getComments().size());
    }

    @Test
    public void createBug_Should_AddBugToList() {
        Bug bug = createValidBug();
        Assertions.assertEquals(1, teamManagementRepository.getTasks().size());
        Assertions.assertEquals(1, teamManagementRepository.getBugs().size());
    }

    @Test
    public void createFeedback_Should_AddFeedbackToList() {
        Feedback feedback = createValidFeedback();
        Assertions.assertEquals(1, teamManagementRepository.getTasks().size());
        Assertions.assertEquals(1, teamManagementRepository.getFeedbacks().size());
    }

    @Test
    public void createStory_Should_AddStoryToList() {
        Story story = createValidStory();
        Assertions.assertEquals(1, teamManagementRepository.getTasks().size());
        Assertions.assertEquals(1, teamManagementRepository.getStories().size());
    }

    private Member createValidMember() {
        return teamManagementRepository.createMember(VALID_MEMBER_NAME);
    }

    private Board createValidBoard() {
        return teamManagementRepository.creteBoard(VALID_BOARD_NAME, VALID_TEAM_NAME);
    }

    private Team createValidTeam() {
        return teamManagementRepository.createTeam(VALID_TEAM_NAME);
    }

    private Bug createValidBug() {
        Member member = createValidMember();
        return teamManagementRepository.createBug(
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH,
                BugSeverityType.CRITICAL,
                member
        );
    }

    private Comment createValidComment() {
        return teamManagementRepository.createComment(
                VALID_COMMENT_AUTHOR,
                VALID_COMMENT_MESSAGE
        );
    }

    private Feedback createValidFeedback() {
        return teamManagementRepository.createFeedback(
                VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                FEEDBACK_RATING,
                FeedbackStatusType.NEW
        );
    }

    private Story createValidStory() {
        Member member = createValidMember();
        return teamManagementRepository.createStory(
                VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                StoryPriorityType.HIGH,
                StorySizeType.LARGE,
                StoryStatusType.IN_PROGRESS,
                member
        );
    }
}

