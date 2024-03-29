package com.iliailievyuliankremenskiood.taskmanagement.models.contracts;

import java.util.List;

public interface Team extends Loggable {
    String getName();

    List<Member> getTeamMembers();

    List<Board> getTeamBoards();

    List<String> getActivityHistory();

    void addMember(Member member);

    void removeMember(Member member);

    void createBoard(Board board);

    void removeBoard(Board board);

    String getActivityInfo();

    String showAllMembers();

    String showAllBoards();
}
