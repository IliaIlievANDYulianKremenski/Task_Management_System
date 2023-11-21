package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ChangeFeedbackStatusCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.exceptions.ElementNotFoundException;
import com.iliailievyuliankremenskiood.taskmanagement.models.FeedbackImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeFeedbackStatusCommandTests {
    private static final String VALID_MEMBER_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private static final String VALID_FEEDBACK_TITLE = "a".repeat(FeedbackImpl.MIN_TITLE_LENGTH + 1);
    private static final String VALID_FEEDBACK_DESCRIPTION = "a".repeat(FeedbackImpl.MIN_DESCRIPTION_LENGTH + 1);
    private static final FeedbackStatusType VALID_FEEDBACK_STATUS = FeedbackStatusType.SCHEDULED;
    private static final FeedbackStatusType VALID_FEEDBACK_STATUS2 = FeedbackStatusType.DONE;
    private static final String INVALID_FEEDBACK_STATUS = "SOMETHING_INVALID";
    private static final int VALID_FEEDBACK_RATING = FeedbackImpl.FEEDBACK_MIN_RATING;
    private TeamManagementRepository teamManagementRepository;
    private ChangeFeedbackStatusCommand changeFeedbackStatusCommand;
    private List<String> parameters;
    private Feedback feedback;
    private Member member;

    @BeforeEach
    private void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        changeFeedbackStatusCommand = new ChangeFeedbackStatusCommand(teamManagementRepository);
        parameters = new ArrayList<>();
        feedback = teamManagementRepository.createFeedback(
                VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING,
                VALID_FEEDBACK_STATUS
        );
        member = new MemberImpl(VALID_MEMBER_NAME);
    }

    @Test
    public void execute_Should_ThrowException_When_ListWithInvalidNumberOfParamsPassed() {
        parameters.add("something");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            changeFeedbackStatusCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidFeedbackIdPassed() {
        parameters.add("100");
        parameters.add(VALID_FEEDBACK_STATUS.toString());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            changeFeedbackStatusCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidStatusPassed() {
        parameters.add("1");
        parameters.add(INVALID_FEEDBACK_STATUS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            changeFeedbackStatusCommand.execute(parameters);
        });
    }

    @Test
    public void execute_Should_executeSuccessfully_When_validParamsPassed() {
        parameters.add("1");
        parameters.add(VALID_FEEDBACK_STATUS2.toString());
        String resultFromTheSuccessfulLChangeOfStatus = changeFeedbackStatusCommand.execute(parameters);
        Assertions.assertEquals(
                feedback.getActivityHistory().get(feedback.getActivityHistory().size() - 1),
                resultFromTheSuccessfulLChangeOfStatus);
    }
}
