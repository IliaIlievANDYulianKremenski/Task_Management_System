package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddCommentToBugCommand;
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
import utils.Tests.TestUtilities;

import java.util.List;

public class AddCommentToBugCommandTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            AddCommentToBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;

    /*<-------Field(s)------->*/

    TeamManagementRepository teamManagementRepository;
    private AddCommentToBugCommand addCommentToBugCommand;

    /*Arrange*/
    @BeforeEach
    public void setAddCommentToBugCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        addCommentToBugCommand = new AddCommentToBugCommand(teamManagementRepository);
    }

    /*<-------Test(s)------->*/
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> list = TestUtilities.createDesiredList(
                DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> addCommentToBugCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_ThrowException_When_BugIdNotNumber() {
        List<String> list = List.of(
                "Bug ID",
                "Author",
                "Comment"
        );
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> addCommentToBugCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_BugDoNotExist() {
        List<String> list = List.of(
                "1",
                "Author",
                "Comment"
        );
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> addCommentToBugCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_AddCommentToBug_When_PassedValidInput() {
    Bug bug = createValidBug();
    List<String> list = List.of(
                "1",
                "Author",
                "Comment"
        );
    addCommentToBugCommand.execute(list);
    Assertions.assertEquals(
            1,
            teamManagementRepository.getBugs().get(0).getComments().size()
    );
        Assertions.assertEquals(
                1,
                teamManagementRepository.getComments().size()
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