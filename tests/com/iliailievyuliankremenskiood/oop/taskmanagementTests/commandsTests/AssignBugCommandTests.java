package com.iliailievyuliankremenskiood.oop.taskmanagementTests.commandsTests;

import com.iliailievyuliankremenskiood.taskmanagement.commands.actions.AssignBugCommand;
import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementRepositoryImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.BugImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.MemberImpl;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Task;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AssignBugCommandTests {
    public static final String VALID_MEMBER_NAME = "A".repeat(MemberImpl.MEMBER_NAME_MIN_LEN);
    public static final int VALID_BUG_ID = 1;
    public static final String VALID_BUG_TITLE = "A".repeat(BugImpl.MIN_TITLE_LENGTH);
    public static final String VALID_BUG_DESCRIPTION = "A".repeat(BugImpl.MIN_DESCRIPTION_LENGTH);

    TeamManagementRepositoryImpl teamManagementRepository;
    AssignBugCommand assignBugCommand;

    Member member;
    Bug bug;
    @BeforeEach
    public void setUp() {
        teamManagementRepository = new TeamManagementRepositoryImpl();
        assignBugCommand = new AssignBugCommand(teamManagementRepository);
        member = createValidMember();
        bug = createValidBug();
    }
    @Test
    public void method() {
        teamManagementRepository.createMember("BBBBBB");
        List<String> list = new ArrayList<>(List.of("1","BBBBBB"));

        assignBugCommand.execute(list);

//        Assertions.assertNotNull();
        Assertions.assertEquals("BBBBBB",bug.getAssignee().getName());
    }

    private Member createValidMember() {
        return teamManagementRepository.createMember(VALID_MEMBER_NAME);
    }
    private Bug createValidBug() {
        Member member = createValidMember();
        return teamManagementRepository.createBug(
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH,
                BugSeverityType.CRITICAL,
                member
        );
    }
}
