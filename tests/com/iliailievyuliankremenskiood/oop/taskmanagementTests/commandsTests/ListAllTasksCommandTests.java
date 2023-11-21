package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ListAllTasksCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllTasksCommandTests {
    private static final String VALID_FEEDBACK_TITlE = "a".repeat(FeedbackImpl.MIN_TITLE_LENGTH);
    private static final String VALID_FEEDBACK_DESCRIPTION = "a".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH);
    private static final FeedbackStatusType VALID_FEEDBACK_STATUS = FeedbackStatusType.SCHEDULED;
    private TeamManagementRepository teamManagementRepository;
    private ListAllTasksCommand listAllTasksCommand;
    private List<String> parameters;
    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        listAllTasksCommand = new ListAllTasksCommand(teamManagementRepository);
        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowException_When_WrongAmountOfParamsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    listAllTasksCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_ThrowException_When_ThereAreNoTasks() {
        parameters.add("ALL_TITLES");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    listAllTasksCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_NotThrowException_When_ThereAreTasksToBeListed() {
        parameters.add(VALID_FEEDBACK_TITlE);
        feedback = teamManagementRepository.createFeedback(
                VALID_FEEDBACK_TITlE,
                VALID_FEEDBACK_DESCRIPTION,
                1,
                VALID_FEEDBACK_STATUS);
        Assertions.assertDoesNotThrow(() -> {
            listAllTasksCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_NotThrowException_When_ThereAreTasksToBeListedAndAllTitlesPassed() {
        parameters.add("ALL_TITLES");
        feedback = teamManagementRepository.createFeedback(
                VALID_FEEDBACK_TITlE,
                VALID_FEEDBACK_DESCRIPTION,
                1,
                VALID_FEEDBACK_STATUS);
        Assertions.assertDoesNotThrow(() -> {
            listAllTasksCommand.execute(parameters);
        });
    }
}
