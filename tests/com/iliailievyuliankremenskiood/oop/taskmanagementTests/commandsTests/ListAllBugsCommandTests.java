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

    /*<-------Constant(s)------->*/

    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllBugsCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private ListAllBugsCommand listAllBugsCommand;

    /*Arrange*/
    @BeforeEach
    public void setListAllBugsCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listAllBugsCommand = new ListAllBugsCommand(teamManagementRepository);

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
                () -> listAllBugsCommand.execute(list)
        );

    }
    @Test
    public void should_ThrowException_When_THereAreNoBugsToList() {
        /*Arrange*/
        List<String> list = List.of(
                "ALL_STATUSES",
                "ALL_ASSIGNEES"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );

    }

    @Test
    public void should_ThrowException_When_THereAreNoBugsToFilterByAssignee() {
        /*Arrange*/
        List<String> list = List.of(
                "Status",
                "ALL_ASSIGNEES"
        );
        /*Act*/
        Bug bug = createValidBug();
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );

    }
    @Test
    public void should_ThrowException_When_THereAreNoBugsInFilteredList() {
        /*Arrange*/
        List<String> list = List.of(
                "ALL_STATUSES",
                "Assignee"
        );
         /*Act*/
         Bug bug = createValidBug();
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> listAllBugsCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        /*Arrange*/
        List<String> list = List.of(
                "ACT",
                "A"
        );
        /*Act*/
        Bug bug = createValidBug();
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> listAllBugsCommand.execute(list)
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
