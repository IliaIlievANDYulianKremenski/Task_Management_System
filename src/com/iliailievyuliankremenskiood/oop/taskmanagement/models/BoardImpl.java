package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Board;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public BoardImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }
}
