package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentImplTests {

    private CommentImpl comment;

    @BeforeEach
    public void setComment() {
        comment = new CommentImpl("Author", "Message");
    }
    @Test
    public void constructor_Should_createComment_When_ValidArgumentsPassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterNamePassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerNamePassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterMessagePassed() {

    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerMessagePassed() {

    }
}
