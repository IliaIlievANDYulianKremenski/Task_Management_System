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

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            AssignBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepositoryImpl teamManagementRepository;
    private AssignBugCommand assignBugCommand;

    /*Arrange*/
    @BeforeEach
    public void setAssignBugCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        assignBugCommand = new AssignBugCommand(teamManagementRepository);
    }

    /*<-------Test(s)------->*/
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        /*Arrange*/
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> assignBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_BugIdNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "Bug ID",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> assignBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_BugDoNotExist() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> assignBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_MemberDoNotExist() {
        /*Arrange*/
        createValidBug();
        List<String> list = List.of(
                "1",
                "B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> assignBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_MemberAlreadyAssigned() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act*/
        createValidBug();
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> assignBugCommand.execute(list)
        );

    }

    @Test
    public void execute_Should_AddCommentToBug_When_PassedValidInput() {
        /*Arrange*/
        Bug bug = createValidBug();
        teamManagementRepository.createMember("B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
        List<String> list = List.of(
                "1",
                "B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act*/
        assignBugCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                "B".repeat(MemberImpl.MEMBER_NAME_MIN_LEN),
                bug.getAssignee().getName()
        );

    }

    /*<-------Helper Method(s)------->*/

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
