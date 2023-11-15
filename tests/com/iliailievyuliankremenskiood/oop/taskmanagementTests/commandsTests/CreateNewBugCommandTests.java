package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewBugCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateNewBugCommandTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            CreateNewBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private CreateNewBugCommand createNewBugCommand;

    /*Arrange*/
    @BeforeEach
    public void setCreateNewBugCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewBugCommand = new CreateNewBugCommand(teamManagementRepository);

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
                () -> createNewBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_PriorityTypeNotValid() {
        /*Arrange*/
        List<String> list = List.of(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                "Not Valid",
                "CRITICAL",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createNewBugCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_SeverityTypeNotValid() {
        /*Arrange*/
        List<String> list = List.of(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                "HIGH",
                "Not Valid",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createNewBugCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_MemberDoNotExist() {
        /*Arrange*/
        List<String> list = List.of(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                "HIGH",
                "CRITICAL",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> createNewBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_CreateNewBug_When_PassedValidInput() {
        List<String> list = List.of(
                "A".repeat(BugImpl.MIN_TITLE_LENGTH),
                "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH),
                "HIGH",
                "CRITICAL",
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN)
        );
        /*Act*/
        Member member = createValidMember();
        createNewBugCommand.execute(list);
        /*Act, Assert*/
        Assertions.assertEquals(
                1,teamManagementRepository.getBugs().size()
        );

    }

    /*<-------Helper Method(s)------->*/

    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
    }
}
