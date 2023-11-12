package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberImplTests {

    private MemberImpl member;
    @BeforeEach
    public void setMember() {
        member = new MemberImpl("Member");
    }
    @Test
    public void constructor_Should_createMember_When_ValidArgumentsPassed() {

    }
    @Test
    public void constructor_Should_initializeTasks_When_ValidArgumentsPassed() {

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
    public void getTasks_Should_ReturnCopyOfTheCollection() {

    }
    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {

    }
    @Test
    public void assignTask_Should_AddTaskToTheCollection() {

    }
    @Test
    public void assignTask_Should_AddHistoryLog() {

    }
    @Test
    public void unassignTask_Should_RemoveTaskToTheCollection() {

    }
    @Test
    public void unassignTask_Should_AddHistoryLog() {

    }

}
