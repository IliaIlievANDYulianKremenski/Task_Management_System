package com.iliailievyuliankremenskiood.taskmanagement.models.contracts;

import java.util.List;

public interface Team extends Loggable{
    String getName();
    List<Member> getMembers();
    List<Board> getBoards();
    List<String> getActivityHistory();
    void addMember(Member member);
    void removeMember(Member member);
    void createBoard(Board board);
    void removeBoard(Board board);
    void showAllMembers();
    void showAllBoards();
}
