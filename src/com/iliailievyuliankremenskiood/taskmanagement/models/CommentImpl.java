package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Comment;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

public class CommentImpl implements Comment {

    /*<-------Constant(s)------->*/
    public static final int AUTHOR_MIN_LEN = 3;
    public static final int AUTHOR_MAX_LEN = 20;
    private static final String AUTHOR_ERR_LEN = "Author name must be between %d and %d characters long.";
    public static final int MESSAGE_MIN_LEN = 1;
    public static final int MESSAGE_MAX_LEN = 200;
    private static final String MESSAGE_ERR_LEN = "Comment must be between %d and %d characters long.";


    /*<-------Field(s)------->*/

    //TODO To change author from String to a Member type.
    private String author;
    private String message;

    public CommentImpl(String author, String message) {
        setAuthor(author);
        setMessage(message);
    }

    /*<-------Getter(s)------->*/

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    /*<-------Setter(s)------->*/

    private void setAuthor(String author) {
        ValidationHelpers.validateStringLength(
                author,
                AUTHOR_MIN_LEN,
                AUTHOR_MAX_LEN,
                String.format(AUTHOR_ERR_LEN,AUTHOR_MIN_LEN,AUTHOR_MAX_LEN)
        );
        this.author = author;
    }

    private void setMessage(String message) {
        ValidationHelpers.validateStringLength(
                message,
                MESSAGE_MIN_LEN,
                MESSAGE_MAX_LEN,
                String.format(MESSAGE_ERR_LEN,MESSAGE_MIN_LEN,MESSAGE_MAX_LEN)
        );
        this.message = message;
    }

    /*<-------Behavioural Method(s)------->*/
    @Override
    public String print() {
        return String.format("""   
                    Author: %s
                    Comment: %s                  
                --------------""",
                getAuthor(),
                getMessage()
        );
    }
}