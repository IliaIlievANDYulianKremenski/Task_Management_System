package com.iliailievyuliankremenskiood.taskmanagement.core;

import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.NameAlreadyExistException;
import com.iliailievyuliankremenskiood.taskmanagement.models.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;

import java.util.ArrayList;
import java.util.List;

public class TeamManagementRepositoryImpl implements TeamManagementRepository {

    /*<-------Constant(s)------->*/
    public static final String CANNOT_FIND_TASK_BY_ID_ERROR_MESSAGE =
            "There is no Bug/Story/Feedback with the provided id: %d.";
    public static final String CANNOT_FIND_BUG_BY_ID_ERROR_MESSAGE =
            "There is no Bug with the provided id: %d.";
    public static final String CANNOT_FIND_STORY_BY_ID_ERROR_MESSAGE =
            "There is no Story with the provided id: %d.";
    public static final String CANNOT_FIND_FEEDBACK_BY_ID_ERROR_MESSAGE =
            "There is no Feedback with the provided id: %d.";
    public static final String NO_TEAM_ERROR_MESSAGE =
            "There is no team with the following name: %s.";
    public static final String NO_BOARD_ERROR_MESSAGE = "There is no Board with the following name: %s.";
    public static final String NO_MEMBER_ERROR_MESSAGE = "There is no team member with the following name: %s.";
    public static final String MEMBER_EXISTS_MESSAGE = "Member with a name %s already exists.";
    public static final String TEAM_EXISTS_MESSAGE = "Team with a name %s already exists.";
    public static final String BOARD_EXISTS_MESSAGE = "Team with a name %s already exists.";


    /*<-------Field(s)------->*/

    private int nextId;
    private final List<Member> members = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();


    /*<-------Constructor(s)------->*/
    public TeamManagementRepositoryImpl() {
        nextId = 1;
    }


    /*<-------Getter(s)------->*/

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }


    /*<-------Behavioural Method(s)------->*/

    @Override
    public Member findMemberByName(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        throw new ElementNotFoundException(
                String.format(NO_MEMBER_ERROR_MESSAGE, memberName));
    }

    @Override
    public Board findBoardByName(String boardName, String teamName) {
        Team team = findTeamByName(teamName);
        for (Board board : team.getTeamBoards()) {
            if (board.getName().equals(boardName)) {
                return board;
            }
        }
        throw new ElementNotFoundException(
                String.format(NO_BOARD_ERROR_MESSAGE, boardName));
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : getTeams()) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new ElementNotFoundException(
                String.format(NO_TEAM_ERROR_MESSAGE, teamName));
    }

    @Override
    public Task findTaskById(int taskId) {
        for (Task task : getTasks()) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        throw new ElementNotFoundException(
                String.format(CANNOT_FIND_TASK_BY_ID_ERROR_MESSAGE, taskId));
    }
    @Override
    public Bug findBugById(int bugId) {
        for (Bug bug : getBugs()) {
            if (bug.getId() == bugId) {
                return bug;
            }
        }
        throw new ElementNotFoundException(
                String.format(CANNOT_FIND_BUG_BY_ID_ERROR_MESSAGE, bugId));
    }
    @Override
    public Story findStoryById(int storyId) {
        for (Story story : getStories()) {
            if (story.getId() == storyId) {
                return story;
            }
        }
        throw new ElementNotFoundException(
                String.format(CANNOT_FIND_STORY_BY_ID_ERROR_MESSAGE, storyId));
    }

    @Override
    public Feedback findFeedbackById(int feedbackId) {
        for (Feedback feedback : getFeedbacks()) {
            if (feedback.getId() == feedbackId) {
                return feedback;
            }
        }
        throw new ElementNotFoundException(
                String.format(CANNOT_FIND_FEEDBACK_BY_ID_ERROR_MESSAGE, feedbackId));
    }

    @Override
    public Member createMember(String memberName) {
        checkIfMemberNameExists(memberName);
        Member temporaryMember = new MemberImpl(memberName);
        members.add(temporaryMember);
        return temporaryMember;
    }



    @Override
    public Team createTeam(String teamName) {
        checkIfTeamNameExists(teamName);
        Team temporaryTeam = new TeamImpl(teamName);
        teams.add(temporaryTeam);
        return temporaryTeam;
    }

    @Override
    public Board creteBoard(String boardName, String teamName) {
        checkIfBoardNameExists(boardName, teamName);
        Board temporaryBoard = new BoardImpl(boardName);
        findTeamByName(teamName).createBoard(temporaryBoard);
        return temporaryBoard;
    }


    @Override
    public Comment createComment(String commentAuthor, String commentMessage) {
        Comment temporaryComment = new CommentImpl(commentAuthor, commentMessage);
        comments.add(temporaryComment);
        return temporaryComment;
    }

    @Override
    public Bug createBug(String title, String description, BugPriorityType bugPriority, BugSeverityType bugSeverity, Member assignee) {
        Bug temporaryBug = new BugImpl(
                nextId++,
                title,
                description,
                bugPriority,
                bugSeverity,
                assignee
        );
        tasks.add(temporaryBug);
        bugs.add(temporaryBug);
        return temporaryBug;
    }

    @Override
    public Feedback createFeedback(String title, String description, int feedbackRating, FeedbackStatusType feedbackStatus) {
        Feedback temporaryFeedback = new FeedbackImpl(
                nextId++,
                title,
                description,
                feedbackRating,
                feedbackStatus
        );
        tasks.add(temporaryFeedback);
        feedbacks.add(temporaryFeedback);
        return temporaryFeedback;
    }

    @Override
    public Story createStory(String title, String description, StoryPriorityType storyPriority, StorySizeType storySize, StoryStatusType storyStatus, Member assignee) {
        Story temporaryStory = new StoryImpl(
                nextId++,
                title,
                description,
                storyPriority,
                storySize,
                storyStatus,
                assignee
        );
        tasks.add(temporaryStory);
        stories.add(temporaryStory);
        return temporaryStory;
    }

    /*<-------Helper Method(s)------->*/

    private void checkIfMemberNameExists(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equals(memberName)) {
                throw new NameAlreadyExistException(String.format(MEMBER_EXISTS_MESSAGE, memberName));
            }
        }
    }
    private void checkIfBoardNameExists(String boardName, String teamName) {
        Team team = findTeamByName(teamName);
        for (Board board : team.getTeamBoards()) {
            if (board.getName().equals(boardName)) {
                throw new NameAlreadyExistException(String.format(BOARD_EXISTS_MESSAGE, boardName));
            }
        }
    }
    private void checkIfTeamNameExists(String teamName) {
        for (Team team : getTeams()) {
            if (team.getName().equals(teamName)) {
                throw new NameAlreadyExistException(String.format(TEAM_EXISTS_MESSAGE, teamName));
            }
        }
    }
}
