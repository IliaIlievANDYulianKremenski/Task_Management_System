package com.iliailievyuliankremenskiood.taskmanagement.utils;

public class ManualCommandPrintHelper {

    /*TODO How to colour text? */
    public static String print() {
        return """
                Commands format:
                \tAdd_Comment_to_Bug {bug ID} {author} {comment}
                \tAdd_Comment_to_Feedback {feedback ID} {author} {comment}
                \tAdd_Comment_to_Story {story ID} {author} {comment}
                \tAdd_Person_to_Team {person name} {team name}
                \tAssign_Bug {bug ID} {assignee}
                \tAssign_Story {story ID} {assignee}
                \tChange_Bug_Priority {bug ID} {new priority}
                \tChange_Bug_Severity {bug ID} {new severity}
                \tChange_Bug_Status {bug ID}
                \tChange_Feedback_Rating {feedback ID} {feedback rating}
                \tChange_Feedback_Status {feedback ID} {new status}
                \tChange_Story_Priority {story ID} {new priority}
                \tChange_Story_Size {story ID} {new size}
                \tChange_Story_Status {story ID} {new status}
                \tCreate_New_Board_In_Team {board name} {team name}
                \tCreate_New_Bug {title} {description} {priority} {severity} {assignee}
                \tCreate_New_Feedback {title} {description} {rating} {status}
                \tCreate_New_Story {title} {description} {priority} {size} {status} {assignee}
                \tCreate_New_Team {team name}
                \tCreate_New_Person {person name}
                \tList_All_Bugs {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
                \tList_All_Feedbacks {filter status / ALL_STATUSES}
                \tList_All_Stories {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
                \tList_All_Tasks {filter title / ALL_TITLES}
                \tList_All_Tasks_With_Assignee {filter status / ALL_STATUSES} {filter assignee / ALL_ASSIGNEES}
                \tManual
                \tShow_All_People
                \tShow_All_Team_Boards {team name}
                \tShow_All_Team_Members {team name}
                \tShow_All_Teams
                \tShow_Board_Activity {board name} {team name}
                \tShow_Person_Activity {person name}
                \tShow_Team_Activity {team name}
                \tUnassign_Bug {bug ID}
                \tUnassign_Story {story ID}
                """;
    }
}
