package com.iliailievyuliankremenskiood.taskmanagement.commands.enums;

public enum CommandType {
    CREATE_NEW_PERSON,
    SHOW_ALL_PEOPLE,
    SHOW_PERSON_ACTIVITY,
    CREATE_NEW_TEAM,
    SHOW_ALL_TEAMS,
    SHOW_TEAM_ACTIVITY,
    ADD_PERSON_TO_TEAM,
    SHOW_ALL_TEAM_MEMBERS,
    CREATE_NEW_BOARD_IN_TEAM,
    SHOW_ALL_TEAM_BOARDS,
    SHOW_BOARD_ACTIVITY,
    CREATE_NEW_BUG,
    CREATE_NEW_STORY,
    CREATE_NEW_FEEDBACK,
    CHANGE_BUG_PRIORITY,
    CHANGE_BUG_SEVERITY,
    CHANGE_BUG_STATUS,
    CHANGE_STORY_PRIORITY,
    CHANGE_STORY_SIZE,
    CHANGE_STORY_STATUS,
    CHANGE_FEEDBACK_RATING,
    CHANGE_FEEDBACK_STATUS,
    ASSIGN_BUG,
    ASSIGN_STORY,
    UNASSIGN_BUG,
    UNASSIGN_STORY,
    ADD_COMMENT_TO_BUG,
    ADD_COMMENT_TO_STORY,
    ADD_COMMENT_TO_FEEDBACK,
    LIST_ALL_TASKS,
    LIST_ALL_BUGS,
    LIST_ALL_STORIES,
    LIST_ALL_FEEDBACKS,
    LIST_TASKS_WITH_ASSIGNEE,
    MANUAL,
    ADD_STEPS_TO_REPRODUCE_TO_BUG,
    ADD_TASK_TO_BOARD,
    SHOW_ALL_TASK_COMMENTS,
    SHOW_STEPS_TO_REPRODUCE,
    SHOW_TASK_ACTIVITY
}