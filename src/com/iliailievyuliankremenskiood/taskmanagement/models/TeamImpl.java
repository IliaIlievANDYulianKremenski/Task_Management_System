package com.iliailievyuliankremenskiood.taskmanagement.models;

import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Team;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.utils.FormatterHelpers;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamImpl implements Team {

    public static final int TEAM_NAME_MIN_LEN = 5;
    public static final int TEAM_NAME_MAX_LEN = 15;
    private static final String TEAM_NAME_ERR_LEN = "Team name must be between %d and %d characters long!";
    private static final String TEAM_MEMBERS_HEADER = "Team %s Members: ";
    private static final String TEAM_BOARDS_HEADER = "Team %s Boards: ";
    private static final String SEPARATOR = "--------------";
    private static final String TEAM_NO_MEMBERS_MESSAGE = "Team %s has no members yet.";
    private static final String TEAM_NO_BOARDS_MESSAGE = "Team %s has no boards yet.";
    private static final String TEAM_CREATE_MESSAGE = "[%s] Team %s has been created.";
    private static final String ADD_MEMBER_MESSAGE = "[%s] %s joined team %s.";
    private static final String REMOVE_MEMBER_MESSAGE = "[%s] %s has left team %s.";
    private static final String CREATE_BOARD_MESSAGE = "[%s] Team %s has created board %s.";
    private static final String REMOVE_BOARD_MESSAGE = "[%s] Team %s has removed board %s.";
    private static final String ACTIVITY_HISTORY_HEADER = "%s activity history: ";
    private String name;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<String> activityHistory;

    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();

        this.activityHistory = new ArrayList<>(
                Arrays.asList(String.format(
                        TEAM_CREATE_MESSAGE,
                        LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                        name))
        );
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Member> getTeamMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getTeamBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(
                name,
                TEAM_NAME_MIN_LEN,
                TEAM_NAME_MAX_LEN,
                String.format(TEAM_NAME_ERR_LEN, TEAM_NAME_MIN_LEN, TEAM_NAME_MAX_LEN)
        );
        this.name = name;
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
        logCreation(String.format(
                ADD_MEMBER_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                member.getName(),
                getName())
        );
    }

    @Override
    public void removeMember(Member member) {
        members.remove(member);
        logCreation(String.format(
                REMOVE_MEMBER_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                member.getName(),
                getName())
        );
    }

    @Override
    public void createBoard(Board board) {
        boards.add(board);
        logCreation(String.format(
                CREATE_BOARD_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                getName(),
                board.getName())
        );
    }

    @Override
    public void removeBoard(Board board) {
        boards.remove(board);
        logCreation(String.format(
                REMOVE_BOARD_MESSAGE,
                LocalDateTime.now().format(FormatterHelpers.dateTimePattern()),
                getName(),
                board.getName())
        );
    }

    @Override
    public String showAllMembers() {
        StringBuilder showMembers = new StringBuilder();
        if (members.isEmpty()) {
            showMembers.append(String.format(
                    TEAM_NO_MEMBERS_MESSAGE,
                    getName())
            );

        } else {
            showMembers.append(String.format(
                    TEAM_MEMBERS_HEADER,
                    getName())
            );
            showMembers.append(System.lineSeparator());
            showMembers.append(SEPARATOR).append(System.lineSeparator());

            for (int i = 0; i < getTeamMembers().size(); i++) {
                showMembers.append(getTeamMembers().get(i).getName());
                showMembers.append(System.lineSeparator());
            }
            showMembers.append(SEPARATOR).append(System.lineSeparator());
        }
        return showMembers.toString().trim();
    }

    @Override
    public String showAllBoards() {
        StringBuilder showBoards = new StringBuilder();
        if (boards.isEmpty()) {
            showBoards.append(String.format(
                    TEAM_NO_BOARDS_MESSAGE,
                    getName())
            );

        } else {
            showBoards.append(SEPARATOR).append(System.lineSeparator());
            showBoards.append(String.format(
                    TEAM_BOARDS_HEADER,
                    getName())
            );
            showBoards.append("\n");
            showBoards.append(SEPARATOR).append(System.lineSeparator());

            for (int i = 0; i < getTeamBoards().size(); i++) {
                showBoards.append(getTeamBoards().get(i).getName());
                showBoards.append(System.lineSeparator());
            }
            showBoards.append(SEPARATOR).append(System.lineSeparator());
        }
        return showBoards.toString().trim();
    }

    public String getActivityInfo() {
        StringBuilder activityInfo = new StringBuilder();
        activityInfo.append(SEPARATOR).append(System.lineSeparator());
        activityInfo.append(String.format(
                ACTIVITY_HISTORY_HEADER,
                getName())
        );
        activityInfo.append(System.lineSeparator());
        activityInfo.append(SEPARATOR).append(System.lineSeparator());

        for (int i = 0; i < getActivityHistory().size(); i++) {
            activityInfo.append(getActivityHistory().get(i));
            activityInfo.append(System.lineSeparator());
        }
        activityInfo.append(SEPARATOR).append(System.lineSeparator());
        return activityInfo.toString().trim();
    }

    @Override
    public void logCreation(String creationString) {
        this.activityHistory.add(creationString);
    }
}