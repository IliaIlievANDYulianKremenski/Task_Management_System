package com.iliailievyuliankremenskiood.oop.taskmanagement.core;

import com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts.CommandFactory;
import com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.oop.taskmanagement.core.contracts.TeamManagementSystemEngine;
import com.iliailievyuliankremenskiood.oop.taskmanagement.commands.contracts.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamManagementSystemEngineImpl implements TeamManagementSystemEngine {
    /*<-------Constant(s)------->*/
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";


    /*<-------Field(s)------->*/
    private final CommandFactory commandFactory;
    private final TeamManagementRepository agencyRepository;


    /*<-------Constructor(s)------->*/
    public TeamManagementSystemEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.agencyRepository = new TeamManagementRepositoryImpl();
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, agencyRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String executionResult = command.execute(parameters);
        System.out.println(executionResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }
}
