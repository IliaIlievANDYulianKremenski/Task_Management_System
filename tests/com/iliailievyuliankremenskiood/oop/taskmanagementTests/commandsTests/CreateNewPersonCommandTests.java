package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.CreateNewPersonCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewPersonCommandTests {
    private static final String VALID_PERSON_NAME = "a".repeat(MemberImpl.MEMBER_NAME_MIN_LEN + 1);
    private TeamManagementRepository teamManagementRepository;
    private CreateNewPersonCommand createNewPersonCommand;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        createNewPersonCommand = new CreateNewPersonCommand(teamManagementRepository);
        parameters = new ArrayList<>();
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAmountOfParamsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    createNewPersonCommand.execute(parameters);
                });
    }

    @Test
    public void execute_Should_createAValidNewPerson_When_ValidParamPassed() {
        parameters.add(VALID_PERSON_NAME);
        String outputFromSuccessfullPersonCreation = createNewPersonCommand.execute(parameters);
        Assertions.assertEquals(
                teamManagementRepository.findMemberByName(VALID_PERSON_NAME).getActivityHistory().get(
                        teamManagementRepository.findMemberByName(VALID_PERSON_NAME).getActivityHistory().size() - 1
                ), outputFromSuccessfullPersonCreation);
    }
}
