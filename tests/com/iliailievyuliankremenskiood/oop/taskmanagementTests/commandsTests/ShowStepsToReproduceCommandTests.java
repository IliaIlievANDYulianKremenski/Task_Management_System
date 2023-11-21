package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowStepsToReproduceCommand;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ShowStepsToReproduceCommandTests {
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_BUG_TITLE = "a".repeat(BugImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_BUG_DESCRIPTION = "a".repeat(BugImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final BugPriorityType VALID_BUG_PRIORITY = BugPriorityType.HIGH;
    private static final BugSeverityType VALID_BUG_SEVERITY = BugSeverityType.CRITICAL;
    public static final String VALID_STEP_TO_REPRODUCE = "Random text.";
    private TeamManagementRepository teamManagementRepository;
    private ShowStepsToReproduceCommand showStepsToReproduceCommand;
    private Bug bug;
    private Member member;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showStepsToReproduceCommand = new ShowStepsToReproduceCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        member = teamManagementRepository.createMember(VALID_MEMBER_NAME);
        bug = teamManagementRepository.createBug(
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                member
        );
        bug.addStepToReproduce("Step 1.");
        bug.addStepToReproduce("Step 2.");
    }

    ;

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountOfArgusProvided() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showStepsToReproduceCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountBugIdProvided() {
        parameters.add("123123123");
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            showStepsToReproduceCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_ShowNotThrowAnyException_When_ValidArgPassed() {
        parameters.add("1");
        Assertions.assertDoesNotThrow(() -> showStepsToReproduceCommand.execute(parameters));
    }
}
