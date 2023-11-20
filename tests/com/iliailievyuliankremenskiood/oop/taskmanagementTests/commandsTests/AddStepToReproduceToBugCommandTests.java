package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AddStepToReproduceToBugCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddStepToReproduceToBugCommandTests {
    /*<-------Constant(s)------->*/
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_BUG_TITLE = "a".repeat(BugImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_BUG_DESCRIPTION = "a".repeat(BugImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final BugPriorityType VALID_BUG_PRIORITY = BugPriorityType.HIGH;
    private static final BugSeverityType VALID_BUG_SEVERITY = BugSeverityType.CRITICAL;
    public static final String VALID_STEP_TO_REPRODUCE = "Random text.";


    /*<-------Field(s)------->*/

    private TeamManagementRepository teamManagementRepository;
    private AddStepToReproduceToBugCommand addStepToReproduceToBugCommand;
    private Bug bug;
    private Member member;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        addStepToReproduceToBugCommand = new AddStepToReproduceToBugCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        bug = teamManagementRepository.createBug(
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountOfArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    addStepToReproduceToBugCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidBugIdProvided() {
        /*Arrange*/
        parameters.add("Random text.");
        parameters.add("123123123");
        /*Act, Assert*/
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> {
                    addStepToReproduceToBugCommand.execute(parameters);
                });
    }


    @Test
    public void execute_Should_AddStepToReproduce_When_ValidArgumentsPassed() {
        /*Arrange*/
        parameters.add(VALID_STEP_TO_REPRODUCE);
        parameters.add("1");

        /*Act*/
        addStepToReproduceToBugCommand.execute(parameters);

        /*Assert*/
        Assertions.assertEquals(bug.getStepsToReproduce().get(0), VALID_STEP_TO_REPRODUCE);
    }
}