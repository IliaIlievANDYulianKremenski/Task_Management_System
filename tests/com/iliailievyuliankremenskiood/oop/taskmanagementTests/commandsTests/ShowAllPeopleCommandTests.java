package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.ShowAllPeopleCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleCommandTests {
    private TeamManagementRepository teamManagementRepository;
    private ShowAllPeopleCommand showAllPeopleCommand;
    private List<String> list;

    @BeforeEach
    public void setShowAllPeopleCommand() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        showAllPeopleCommand = new ShowAllPeopleCommand(teamManagementRepository);
        list = new ArrayList<>();
    }

    @Test
    public void should_ThrowException_When_THereAreNoMembersToList() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> showAllPeopleCommand.execute(list)
        );
    }

    @Test
    public void execute_Should_NotThrowException_When_PassedValidInput() {
        Member member = createValidMember();
        Assertions.assertDoesNotThrow(
                () -> showAllPeopleCommand.execute(list)
        );
    }

    private Member createValidMember() {
        return teamManagementRepository.createMember(
                "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN));
    }
}
