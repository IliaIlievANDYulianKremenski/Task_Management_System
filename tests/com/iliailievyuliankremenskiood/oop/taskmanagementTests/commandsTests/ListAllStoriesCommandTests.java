package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllStoriesCommand;
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

import java.util.List;

public class ListAllStoriesCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllStoriesCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ListAllStoriesCommand listAllStoriesCommand;

    @BeforeEach
    public void setListAllStoriesCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listAllStoriesCommand = new ListAllStoriesCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllStoriesCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoStoriesToList() {
        List<String> list = List.of(
                "ALL_STATUSES",
                "ALL_ASSIGNEES"
        );
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllStoriesCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoStoriesToFilterByAssignee() {
        List<String> list = List.of(
                "Status",
                "ALL_ASSIGNEES"
        );
        Story story = createValidStory();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllStoriesCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoStoriesInFilteredList() {
        List<String> list = List.of(
                "ALL_STATUSES",
                "Assignee"
        );
        Story story = createValidStory();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllStoriesCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        List<String> list = List.of(
                "In",
                "A"
        );
        Story story = createValidStory();
        Assertions.assertDoesNotThrow(
                () -> listAllStoriesCommand.execute(list)
        );
    }

    private Story createValidStory() {
        Member member = createValidMember();
        return teamManagementRepository.createStory(
                "A".repeat(StoryImpl.MIN_TITLE_LENGTH),
                "A".repeat(StoryImpl.MIN_DESCRIPTION_LENGTH),
                StoryPriorityType.HIGH,
                StorySizeType.LARGE,
                StoryStatusType.IN_PROGRESS,
                member
        );
    }

    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
    }
}