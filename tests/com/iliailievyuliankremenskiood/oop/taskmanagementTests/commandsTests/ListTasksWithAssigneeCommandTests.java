package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListTasksWithAssigneeCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.StoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTasksWithAssigneeCommandTests {
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN);
    private static final String VALID_TASK_TITLE = "a".repeat(StoryImpl.MIN_TITLE_LENGTH);
    private static final String VALID_TASK_DESCRIPTION = "a".repeat(BugImpl.MIN_DESCRIPTION_LENGTH);
    private static final StoryPriorityType VALID_STORY_PRIORITY = StoryPriorityType.HIGH;
    private static final StorySizeType VALID_STORY_SIZE = StorySizeType.LARGE;
    private static final StoryStatusType VALID_STORY_STATUS = StoryStatusType.IN_PROGRESS;
    private static final BugPriorityType VALID_BUG_PRIORITY = BugPriorityType.HIGH;
    private static final BugSeverityType VALID_BUG_SEVERITY = BugSeverityType.CRITICAL;
    private static final BugStatusType VALID_BUG_STATUS = BugStatusType.DONE;

    private static final BugStatusType BUG_STATUS_ACTIVE = BugStatusType.ACTIVE;
    private TeamManagementRepository teamManagementRepository;
    private ListTasksWithAssigneeCommand listTasksWithAssigneeCommand;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listTasksWithAssigneeCommand = new ListTasksWithAssigneeCommand(teamManagementRepository);
        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountOfArgsPassed() {
        parameters.add("Test");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    listTasksWithAssigneeCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_ThereAreNoTasksWithAssigneeToBeListed() {
        parameters.add("ALL_STATUSES");
        parameters.add("ALL_ASSIGNEES");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    listTasksWithAssigneeCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_NotThrowException_When_AllStatusesAllAssigneesPassed() {
        parameters.add("ALL_STATUSES");
        parameters.add("ALL_ASSIGNEES");
        Member member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        teamManagementRepository.createStory(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        teamManagementRepository.createBug(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
        Assertions.assertDoesNotThrow(() -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_NotThrowException_When_AllStatusesSpecificAssigneeNamePassed() {
        parameters.add("ALL_STATUSES");
        parameters.add(VALID_MEMBER_NAME);
        Member member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        teamManagementRepository.createStory(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        teamManagementRepository.createBug(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
        Assertions.assertDoesNotThrow(() -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_NotThrowException_When_AllAssigneesSpecificStoryStatusPassed() {
        parameters.add(VALID_STORY_STATUS.toString());
        parameters.add("ALL_ASSIGNEES");
        Member member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        teamManagementRepository.createStory(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        teamManagementRepository.createBug(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
        Assertions.assertDoesNotThrow(() -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_NotThrowException_When_AllAssigneesSpecificBugStatusPassed() {
        parameters.add(VALID_BUG_STATUS.toString());
        parameters.add("ALL_ASSIGNEES");
        Member member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        teamManagementRepository.createStory(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        teamManagementRepository.createBug(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
        Assertions.assertDoesNotThrow(() -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_NotThrowException_When_SpecificStatusAndAssiggneePassed() {
        parameters.add(VALID_BUG_STATUS.toString());
        parameters.add(VALID_MEMBER_NAME);
        Member member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        teamManagementRepository.createStory(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        teamManagementRepository.createBug(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
        Assertions.assertDoesNotThrow(() -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_NotThrowException_When_ActiveStatusAndAssiggneePassed() {
        parameters.add(BUG_STATUS_ACTIVE.toString());
        parameters.add(VALID_MEMBER_NAME);
        Member member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        teamManagementRepository.createStory(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                member);
        teamManagementRepository.createBug(
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
        Assertions.assertDoesNotThrow(() -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }
}
