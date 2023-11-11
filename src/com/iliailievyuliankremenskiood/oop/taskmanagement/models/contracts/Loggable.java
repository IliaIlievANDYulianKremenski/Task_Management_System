package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

public interface Loggable {
    /*‼️TODO‼️:
        Speak with Ilia on the fact that we would like to keep this method
        private (so that only the classes that implement it can see it and use it).
        If we put it in the interface, the access modifier automatically becomes
        "public".
        Not sure this is a good idea.
     */
    void logEvent(String attributeForWhichWeAreLogging, String attributeContent);
    /*‼️TODO‼️:
      Speak with Ilia on the fact that every Task has methods
    * getComments() and getActivityHistory() which can actually be considered
    * as the logs of the task.
    * With this in mind - should we have a method getLogs()???*/
    /*    void getLogs();*/
}
