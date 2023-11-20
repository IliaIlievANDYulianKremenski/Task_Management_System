package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.oop.taskmanagementTests.utils.Tests.TestUtilities;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllBugsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowAllTaskCommentsCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.*;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllTaskCommentsTests {

    /*<-------Constant(s)------->*/
    private static final int DIFFERENT_THAN_EXPECTED_NUMBER_OF_ARGUMENTS =
            ListAllBugsCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1;


    /*<-------Field(s)------->*/
    private TeamManagementRepository teamManagementRepository;
    private ShowAllTaskCommentsCommand showAllTaskCommentsCommand;

    /*Arrange*/
    @BeforeEach
    public void setShowAllTaskCommentsCommand(){
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showAllTaskCommentsCommand = new ShowAllTaskCommentsCommand(teamManagementRepository);

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
                () -> showAllTaskCommentsCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_TaskIdNotNumber() {
        /*Arrange*/
        List<String> list = List.of(
                "Task ID"

        );
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> showAllTaskCommentsCommand.execute(list)
        );
    }
    @Test
    public void execute_Should_ThrowException_When_BugDoNotExist() {
        /*Arrange*/
        List<String> list = List.of(
                "1"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> showAllTaskCommentsCommand.execute(list)
        );
    }
    @Test
    public void execute_ThrowException_When_ThereAreNoComments() {
        /*Arrange*/
        Bug bug = createValidBug();
        List<String> list = List.of(
                "1"
        );
        /*Act, Assert*/
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showAllTaskCommentsCommand.execute(list)
        );

    }
    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        /*Arrange*/
        Bug bug = createValidBug();
        List<String> list = List.of(
                "1"
        );
        Comment comment = createValidComment();
        bug.addCommentToTask(comment);
        /*Act, Assert*/
        Assertions.assertDoesNotThrow(
                () -> showAllTaskCommentsCommand.execute(list)
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
    private Comment createValidComment() {
        return teamManagementRepository.createComment(
                "A".repeat(CommentImpl.AUTHOR_MIN_LEN),
                "A".repeat(CommentImpl.MESSAGE_MIN_LEN)
                );
    }
}
