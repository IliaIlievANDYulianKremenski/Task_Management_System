package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamImplTets {

    private TeamImpl team;

    @BeforeEach
    public void setTeam() {
        team = new TeamImpl("Team5");
    }
    @Test
    public void constructor_Should_createTeam_When_ValidArgumentsPassed() {

    }
    @Test
    public void constructor_Should_initializeMembers_When_ValidArgumentsPassed() {

    }
    @Test
    public void constructor_Should_initializeBoards_When_ValidArgumentsPassed() {

    }
    @Test
    public void constructor_Should_logActivityHistory_When_ValidArgumentsPassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterNamePassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerNamePassed() {

    }
    @Test
    public void getBoards_Should_ReturnCopyOfTheCollection() {

    }
    @Test
    public void getMembers_Should_ReturnCopyOfTheCollection() {

    }
    @Test
    public void addMember_Should_AddMemberToTheCollection() {

    }
    @Test
    public void addMember_Should_AddHistoryLog() {

    }
    @Test
    public void removeMember_Should_RemoveMemberFromTheCollection() {

    }
    @Test
    public void removeMember_Should_AddHistoryLog() {

    }
    @Test
    public void createBoard_Should_AddBoardToTheCollection() {

    }
    @Test
    public void createBoard_Should_AddHistoryLog() {

    }
    @Test
    public void removeBoard_Should_RemoveBoardFromTheCollection() {

    }

    @Test
    public void removeBoard_should_AddHistoryLog() {

    }

}
