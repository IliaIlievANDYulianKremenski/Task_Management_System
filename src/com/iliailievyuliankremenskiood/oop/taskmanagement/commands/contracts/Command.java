package com.iliailievyuliankremenskiood.oop.taskmanagement.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);

}
