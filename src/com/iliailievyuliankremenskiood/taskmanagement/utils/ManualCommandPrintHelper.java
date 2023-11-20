package com.iliailievyuliankremenskiood.taskmanagement.utils;

public class ManualCommandPrintHelper {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";


    public static String print() {
        return ANSI_BLUE + "Commands format:\n" + ANSI_RESET +
                "\tAdd_Comment_to_Bug " + ANSI_GREEN + "--{bug ID} --{author} --{comment}\n" + ANSI_RESET +
                "\tAdd_Comment_to_Feedback " + ANSI_GREEN + "--{feedback ID} --{author} --{comment}\n" + ANSI_RESET +
                "\tAdd_Comment_to_Story " + ANSI_GREEN + "--{story ID} --{author} --{comment}\n" + ANSI_RESET +
                "\tAdd_Person_to_Team " + ANSI_GREEN + "--{person name} --{team name}\n" + ANSI_RESET +
                "\tAdd_Step_To_Reproduce_To_Bug " + ANSI_GREEN + "--{step to reproduce} --{bug ID}\n" + ANSI_RESET +
                "\tAdd_Task_to_Board " + ANSI_GREEN + "--{task ID} --{board name} --{team name}\n" + ANSI_RESET +
                "\tAssign_Bug " + ANSI_GREEN + "--{bug ID} --{assignee}\n" + ANSI_RESET +
                "\tAssign_Story " + ANSI_GREEN + "--{story ID} --{assignee}\n" + ANSI_RESET +
                "\tChange_Bug_Priority " + ANSI_GREEN + "--{bug ID} --{new priority}\n" + ANSI_RESET +
                "\tChange_Bug_Severity " + ANSI_GREEN + "--{bug ID} --{new severity}\n" + ANSI_RESET +
                "\tChange_Bug_Status " + ANSI_GREEN + "--{bug ID}\n" + ANSI_RESET +
                "\tChange_Feedback_Rating " + ANSI_GREEN + "--{feedback ID} --{feedback rating}\n" + ANSI_RESET +
                "\tChange_Feedback_Status " + ANSI_GREEN + "--{feedback ID} --{new status}\n" + ANSI_RESET +
                "\tChange_Story_Priority " + ANSI_GREEN + "--{story ID} --{new priority}\n" + ANSI_RESET +
                "\tChange_Story_Size " + ANSI_GREEN + "--{story ID} --{new size}\n" + ANSI_RESET +
                "\tChange_Story_Status " + ANSI_GREEN + "--{story ID} --{new status}\n" + ANSI_RESET +
                "\tCreate_New_Board_In_Team " + ANSI_GREEN + "--{board name} --{team name}\n" + ANSI_RESET +
                "\tCreate_New_Bug " + ANSI_GREEN + "--{title} --{description} --{priority} --{severity} --{assignee}\n" + ANSI_RESET +
                "\tCreate_New_Feedback " + ANSI_GREEN + "--{title} --{description} --{rating} --{status}\n" + ANSI_RESET +
                "\tCreate_New_Story " + ANSI_GREEN + "--{title} --{description} --{priority} --{size} --{status} --{assignee}\n" + ANSI_RESET +
                "\tCreate_New_Team " + ANSI_GREEN + "--{team name}\n" + ANSI_RESET +
                "\tCreate_New_Person " + ANSI_GREEN + "--{person name}\n" + ANSI_RESET +
                "\tList_All_Bugs " + ANSI_GREEN + "--{filter status / ALL_STATUSES} --{filter assignee / ALL_ASSIGNEES}\n" + ANSI_RESET +
                "\tList_All_Feedbacks " + ANSI_GREEN + "--{filter status / ALL_STATUSES}\n" + ANSI_RESET +
                "\tList_All_Stories " + ANSI_GREEN + "--{filter status / ALL_STATUSES} --{filter assignee / ALL_ASSIGNEES}\n" + ANSI_RESET +
                "\tList_All_Tasks " + ANSI_GREEN + "--{filter title / ALL_TITLES}\n" + ANSI_RESET +
                "\tList_Tasks_With_Assignee " + ANSI_GREEN + "--{filter status / ALL_STATUSES} --{filter assignee / ALL_ASSIGNEES}\n" + ANSI_RESET +
                "\tManual\n" + ANSI_RESET +
                "\tShow_All_People\n" + ANSI_RESET +
                "\tShow_All_Task_Comments " + ANSI_GREEN + "--{task ID}\n" + ANSI_RESET +
                "\tShow_All_Team_Boards " + ANSI_GREEN + "--{team name}\n" + ANSI_RESET +
                "\tShow_All_Team_Members " + ANSI_GREEN + "--{team name}\n" + ANSI_RESET +
                "\tShow_All_Teams\n" + ANSI_RESET +
                "\tShow_Board_Activity " + ANSI_GREEN + "--{board name} --{team name}\n" + ANSI_RESET +
                "\tShow_Person_Activity " + ANSI_GREEN +" --{person name}\n" + ANSI_RESET +
                "\tShow_Steps_To_Reproduce " + ANSI_GREEN +" --{bug ID}\n" + ANSI_RESET +
                "\tShow_Task_Activity " + ANSI_GREEN + "--{task ID}\n" + ANSI_RESET +
                "\tShow_Team_Activity " + ANSI_GREEN + "--{team name}\n" + ANSI_RESET +
                "\tUnassign_Bug " + ANSI_GREEN + "--{bug ID}\n" + ANSI_RESET +
                "\tUnassign_Story " + ANSI_GREEN + "--{story ID}" + ANSI_RESET;
    }

}
