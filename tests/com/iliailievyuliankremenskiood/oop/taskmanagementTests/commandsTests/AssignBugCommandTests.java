package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AssignBugCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
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


public class AssignBugCommandTests {
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            AssignBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;
    private TeamManagementRepositoryImpl teamManagementRepository;
    private AssignBugCommand assignBugCommand;

    @BeforeEach
    public void setAssignBugCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        assignBugCommand = new AssignBugCommand(teamManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> assignBugCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_BugIdNotNumber() {
        List<String> list = List.of(
                "Bug ID",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> assignBugCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_BugDoNotExist() {
        List<String> list = List.of(
                "1",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> assignBugCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_MemberDoNotExist() {
        createValidBug();
        List<String> list = List.of(
                "1",
                "B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> assignBugCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_ThrowException_When_MemberAlreadyAssigned() {
        List<String> list = List.of(
                "1",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        createValidBug();
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> assignBugCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_AddCommentToBug_When_PassedValidInput() {
        Bug bug = createValidBug();
        teamManagementRepository.createMember("B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
        List<String> list = List.of(
                "1",
                "B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        assignBugCommand.execute(list);
        Assertions.assertEquals(
                "B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN),
                bug.getAssignee().getName()
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
