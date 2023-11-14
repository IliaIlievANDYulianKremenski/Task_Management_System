package com.iliailievyuliankremenskiood.taskmanagement.core.contracts;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;

import java.util.List;

public interface TeamManagementRepository {
    List<Member> getMembers();

    List<Task> getTasks();

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    List<Team> getTeams();

    List<Board> getBoards();

    List<Comment> getComments();

    Member findMemberByName(String memberName);

    Board findBoardByName(String boardName);

    Team findTeamByName(String teamName);

    Task findTaskById(int taskId);

    Bug findBugById(int bugId);

    Story findStoryById(int storyId);

    Feedback findFeedbackById(int feedbackId);

    Member createMember(String memberName);

    Team createTeam(String teamName);

    Board creteBoard(String boardName);

    Comment createComment(String commentAuthor, String commentMessage);

    Bug createBug(String title, String description,
                  BugPriorityType bugPriority, BugSeverityType bugSeverity, Member assignee);

    Feedback createFeedback(String title, String description,
                            int feedbackRating, FeedbackStatusType feedbackStatus);

    Story createStory(String title, String description,
                      StoryPriorityType storyPriority, StorySizeType storySize, StoryStatusType storyStatus,
                      Member assignee);
}
