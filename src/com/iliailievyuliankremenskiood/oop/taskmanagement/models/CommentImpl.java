package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Comment;

public class CommentImpl implements Comment {

    /*<-------Constant(s)------->*/

    //TODO
    /*Talk with Yuli if it is a good idea if we choose to use some limitations
    when setting author and message just like other classes. Limitations about name length
    and message length.
     */

    /*<-------Field(s)------->*/

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
        this.author = author;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    /*<-------Behavioural Method(s)------->*/
    @Override
    public String print() {
        return String.format("""
                --------------    
                    Author: %s
                    Comment: %s                  
                --------------""",
                getAuthor(),
                getMessage()
        );
    }
}