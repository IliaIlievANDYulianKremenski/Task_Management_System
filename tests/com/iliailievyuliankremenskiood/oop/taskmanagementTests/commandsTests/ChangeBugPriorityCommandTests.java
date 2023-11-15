package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ChangeBugPriorityCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
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

public class ChangeBugPriorityCommandTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ChangeBugPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    TeamManagementRepository teamManagementRepository;
    private ChangeBugPriorityCommand changeBugPriorityCommand;

    /*Arrange*/
    @BeforeEach
    public void setChangeBugPriorityCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        changeBugPriorityCommand = new ChangeBugPriorityCommand(teamManagementRepository);
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
                () -> changeBugPriorityCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_BugIdNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "Bug ID",
                "MEDIUM"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> changeBugPriorityCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_PriorityTypeNotValid() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                "Not Valid"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> changeBugPriorityCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_BugDoNotExist() {
        /*Arrange*/
        List<String> list = List.of(
                "1",
                "MEDIUM"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> changeBugPriorityCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ChangeBugPriority_When_PassedValidInput() {
        /*Arrange*/
        Bug bug = createValidBug();
        List<String> list = List.of(
                "1",
                "MEDIUM"
        );
        /*Act*/
        changeBugPriorityCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                "MEDIUM",
                teamManagementRepository.getBugs().get(0).getPriority().toString()
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
