package com.iliailievyuliankremenskiood.oop.taskmanagement.core;

import com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.*;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.*;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;

import java.util.ArrayList;
import java.util.List;

public class TeamManagementRepositoryImpl implements TeamManagementRepository {

    /*<-------Constant(s)------->*/
    public static final String CANNOT_FIND_TASK_BY_ID_ERROR_MESSAGE =
            "There is no Bug/Story/Feedback with the provided id: %d.";
    public static final String NO_TEAM_ERROR_MESSAGE =
            "There is no team with the following name: %s.";
    public static final String NO_BOARD_ERROR_MESSAGE = "There is no Board with the following name: %s.";
    public static final String NO_MEMBER_ERROR_MESSAGE = "There is no team member with the following name: %s.";


    /*<-------Field(s)------->*/
    private int nextId;
    private final List<Member> members = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();


    /*<-------Constructor(s)------->*/
    public TeamManagementRepositoryImpl() {
        nextId = 1;
    }


    /*<-------Getter(s)------->*/

    @Override
    public List<Member> getMember() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public Member findMemberByName(String memberName) {
        for (Member member : getMember()) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        throw new ElementNotFoundException(
                String.format(NO_MEMBER_ERROR_MESSAGE, memberName));
    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Board board : getBoards()) {
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
    public Member createMember(String memberName) {
        Member temporaryMember = new MemberImpl(memberName);
        members.add(temporaryMember);
        return temporaryMember;
    }

    @Override
    public Team createTeam(String teamName) {
        Team temporaryTeam = new TeamImpl(teamName);
        teams.add(temporaryTeam);
        return temporaryTeam;
    }

    @Override
    public Board creteBoard(String boardName) {
        Board temporaryBoard = new BoardImpl(boardName);
        boards.add(temporaryBoard);
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
        Bug temporaryBug = new BugImpl(nextId++, title, description, bugPriority, bugSeverity, assignee);
        tasks.add(temporaryBug);
        return temporaryBug;
    }

    @Override
    public Feedback createFeedback(String title, String description, int feedbackRating, FeedbackStatusType feedbackStatus) {
        Feedback temporaryFeedback = new FeedbackImpl(nextId++, title, description, feedbackRating, feedbackStatus);
        tasks.add(temporaryFeedback);
        return temporaryFeedback;
    }

    @Override
    public Story createStory(String title, String description, StoryPriorityType storyPriority, StorySizeType storySize, StoryStatusType storyStatus, Member assignee) {
        Story temporaryStory = new StoryImpl(nextId++, title, description, storyPriority, storySize, storyStatus, assignee);
        tasks.add(temporaryStory);
        return temporaryStory;
    }


}
