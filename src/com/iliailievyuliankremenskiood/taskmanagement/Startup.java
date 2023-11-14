package com.iliailievyuliankremenskiood.taskmanagement;

import com.iliailievyuliankremenskiood.taskmanagement.core.TeamManagementSystemEngineImpl;

public class Startup {
    public static void main(String[] args) {

        TeamManagementSystemEngineImpl engine = new TeamManagementSystemEngineImpl();
        engine.start();
    }
}