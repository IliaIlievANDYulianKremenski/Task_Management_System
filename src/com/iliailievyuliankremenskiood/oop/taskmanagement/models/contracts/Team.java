package com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts;

import java.util.List;

public interface Team {
    String getName();
    List<Member> getMembers();
    List<Board> getBoards();
    void addMember(Member member);
    void removeMember(Member member);
    void createBoard(Board board);
    void removeBoard(Board board);
    void showAllMembers();
    void showAllBoards();
}
