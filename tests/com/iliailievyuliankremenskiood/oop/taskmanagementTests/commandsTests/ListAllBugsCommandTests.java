package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllBugsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ListAllBugsCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllBugsCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepository teamManagementRepository;
    private ListAllBugsCommand listAllBugsCommand;

    @BeforeEach
    public void setListAllBugsCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listAllBugsCommand = new ListAllBugsCommand(teamManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoBugsToList() {
        List<String> list = List.of(
                "ALL_STATUSES",
                "ALL_ASSIGNEES"
        );
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoBugsToFilterByAssignee() {
        List<String> list = List.of(
                "Status",
                "ALL_ASSIGNEES"
        );
        createValidBug();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );
    }

    @Test
    public void should_ThrowException_When_THereAreNoBugsInFilteredList() {
        List<String> list = List.of(
                "ALL_STATUSES",
                "Assignee"
        );
        createValidBug();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        List<String> list = List.of(
                "ACT",
                "A"
        );
        createValidBug();
        Assertions.assertDoesNotThrow(
                () -> listAllBugsCommand.execute(list)
        );
    }

    private Bug createValidBug() {
        Member member = createValidMember();
        return teamManagementRepository.createBug(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                BugPriorityType.HIGH,
                BugSeverityType.CRITICAL,
                member
        );
    }

    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
    }
}
