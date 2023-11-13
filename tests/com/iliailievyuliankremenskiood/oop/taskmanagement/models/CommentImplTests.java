package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentImplTests {

    /*<-------Constant(s)------->*/

    public static final String VALID_NAME = "A".repeat(CommentImpl.AUTHOR_MIN_LEN);
    public static final String VALID_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MIN_LEN);
    public static final String SHORT_NAME = "A".repeat(CommentImpl.AUTHOR_MIN_LEN-1);
    public static final String LONG_NAME = "A".repeat(CommentImpl.AUTHOR_MAX_LEN+1);
    public static final String SHORT_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MIN_LEN-1);
    public static final String LONG_MESSAGE = "A".repeat(CommentImpl.MESSAGE_MAX_LEN+1);

    /*<-------Field(s)------->*/

    private CommentImpl comment;

    /*Arrange*/
    @BeforeEach
    public void setComment() {
        comment = new CommentImpl(VALID_NAME, VALID_MESSAGE);
    }

    /*<-------Test(s)------->*/

    @Test
    public void constructor_Should_createComment_When_ValidArgumentsPassed() {
        /*Act, Assert*/
        Assertions.assertEquals(VALID_NAME,comment.getAuthor());
        Assertions.assertEquals(VALID_MESSAGE,comment.getMessage());
    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(SHORT_NAME,VALID_MESSAGE)
        );
    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerNamePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(LONG_NAME,VALID_MESSAGE)
        );
    }
    @Test
    public void Constructor_Should_ThrowError_When_ShorterMessagePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(SHORT_MESSAGE,VALID_MESSAGE)
        );
    }
    @Test
    public void Constructor_Should_ThrowError_When_LongerMessagePassed() {
        /*Act, Assert*/
        Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> new CommentImpl(LONG_MESSAGE,VALID_MESSAGE)
        );
    }
}
