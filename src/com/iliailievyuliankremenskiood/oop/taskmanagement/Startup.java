package com.iliailievyuliankremenskiood.oop.taskmanagement;

import com.iliailievyuliankremenskiood.oop.taskmanagement.core.TeamManagementSystemEngineImpl;

public class Startup {
    public static void main(String[] args) {

        TeamManagementSystemEngineImpl engine = new TeamManagementSystemEngineImpl();
        engine.start();
    }
}