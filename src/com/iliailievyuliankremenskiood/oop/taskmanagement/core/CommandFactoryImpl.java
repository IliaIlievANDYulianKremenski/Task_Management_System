package com.iliailievyuliankremenskiood.oop.taskmanagement.core;

import com.iliailievyuliankremenskiood.oop.taskmanagement.commands.actions.*;
import com.iliailievyuliankremenskiood.oop.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.oop.taskmanagement.commands.enums.CommandType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts.CommandFactory;
import com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.oop.taskmanagement.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommandFromCommandName(String commandName, TeamManagementRepository teamManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class, String.format(INVALID_COMMAND, commandName));

        switch (commandType) {
            case CREATE_PERSON:
                return new CreatePersonCommand(teamManagementRepository);
            case SHOW_ALL_PEOPLE:
                return new ShowAllPeopleCommand(teamManagementRepository);
            case SHOW_PERSON_ACTIVITY:
                return new ShowPersonActivityCommand(teamManagementRepository);
            case CREATE_NEW_TEAM:
                return new CreateNewTeamCommand(teamManagementRepository);
            case SHOW_ALL_TEAMS:
                return new ShowAllTeamsCommand(teamManagementRepository);
            case SHOW_TEAM_ACTIVITY:
                return new ShowTeamActivityCommand(teamManagementRepository);
            case ADD_PERSON_TO_TEAM:
                return new AddPersonToTeamCommand(teamManagementRepository);
            case CREATE_NEW_BOARD_IN_TEAM:
                return new CreateNewBoardInTeamCommand(teamManagementRepository);
            case SHOW_ALL_TEAM_BOARDS:
                return new ShowAllTeamBoardsCommand(teamManagementRepository);
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(teamManagementRepository);
            case CREATE_NEW_BUG:
                return new CreateNewBugCommand(teamManagementRepository);
            case CREATE_NEW_STORY:
                return new CreateNewStoryCommand(teamManagementRepository);
            case CREATE_NEW_FEEDBACK:
                return new CreateNewFeedbackCommand(teamManagementRepository);
            case CHANGE_BUG_PRIORITY:
                return new ChangeBugPriorityCommand(teamManagementRepository);
            case CHANGE_BUG_SEVERITY:
                return new ChangeBugSeverityCommand(teamManagementRepository);
            case CHANGE_BUG_STATUS:
                return new ChangeBugStatusCommand(teamManagementRepository);
            case CHANGE_STORY_PRIORITY:
                return new ChangeStoryPriorityCommand(teamManagementRepository);
            case CHANGE_STORY_SIZE:
                return new ChangeStorySizeCommand(teamManagementRepository);
            case CHANGE_STORY_STATUS:
                return new ChangeStoryStatusCommand(teamManagementRepository);
            case CHANGE_FEEDBACK_RATING:
                return new ChangeFeedbackRatingCommand(teamManagementRepository);
            case CHANGE_FEEDBACK_STATUS:
                return new ChangeFeedbackStatusCommand(teamManagementRepository);
            case ASSIGN_BUG:
                return new AssignBugCommand(teamManagementRepository);
            case ASSIGN_STORY:
                return new AssignStoryCommand(teamManagementRepository);
            case UNASSIGN_BUG:
                return new UnassignBugCommand(teamManagementRepository);
            case UNASSIGN_STORY:
                return new UnassignStoryCommand(teamManagementRepository);
            case ADD_COMMENT_TO_BUG:
                return new AddCommentToBugCommand(teamManagementRepository);
            case ADD_COMMENT_TO_STORY:
                return new AddCommentToStoryCommand(teamManagementRepository);
            case ADD_COMMENT_TO_FEEDBACK:
                return new AddCommentToFeedbackCommand(teamManagementRepository);
            case LIST_ALL_TASKS:
                return new ListAllTasksCommnd(teamManagementRepository);
            case LIST_ALL_BUGS:
                return new ListAllBugsCommand(teamManagementRepository);
            case LIST_ALL_STORIES:
                return new ListAllStoriesCommand(teamManagementRepository);
            case LIST_ALL_FEEDBACKS:
                return new ListAllFeedbacksCommand(teamManagementRepository);
            case LIST_TASKS_WITH_ASSIGNEE:
                return new ListTasksWithAssigneeCommand(teamManagementRepository);
            case SHOW_ALL_TEAM_MEMBERS:
                return new ShowAllTeamMembersCommand(teamManagementRepository);
            case MANUAL:
                return new ManualCommand(teamManagementRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
        }
    }
}
