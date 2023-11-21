package com.iliailievyuliankremenskiood.oop.taskmanagementTests.modelsTests;

import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;
import com.iliailievyuliankremenskiood.taskmanagement.models.CommentImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentImplTests {
    public static final String VALID_NAME = "A".repeat(CommentImpl.AUTHOR_MIN_LEN);
    public static final String VALID_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MIN_LEN);
    public static final String SHORT_NAME = "A".repeat(CommentImpl.AUTHOR_MIN_LEN - 1);
    public static final String LONG_NAME = "A".repeat(CommentImpl.AUTHOR_MAX_LEN + 1);
    public static final String SHORT_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MIN_LEN - 1);
    public static final String LONG_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MAX_LEN + 1);
    private CommentImpl comment;

    @BeforeEach
    public void setComment() {
        comment = new CommentImpl(VALID_NAME, VALID_MESSAGE);
    }

    @Test
    public void constructor_Should_createComment_When_ValidArgumentsPassed() {
        Assertions.assertEquals(VALID_NAME, comment.getAuthor());
        Assertions.assertEquals(VALID_MESSAGE, comment.getMessage());
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterNamePassed() {
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(SHORT_NAME, VALID_MESSAGE)
        );
    }

    @Test
    public void constructor_Should_ThrowError_When_LongerNamePassed() {
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(LONG_NAME, VALID_MESSAGE)
        );
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterMessagePassed() {
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(SHORT_MESSAGE, VALID_MESSAGE)
        );
    }

    @Test
    public void constructor_Should_ThrowError_When_LongerMessagePassed() {
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(LONG_MESSAGE, VALID_MESSAGE)
        );
    }

    @Test
    public void print_Should_NotThrowException_When_MethodCalled() {
        Assertions.assertDoesNotThrow(
                () -> comment.print()
        );
    }
}
