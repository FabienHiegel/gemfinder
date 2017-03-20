package com.dedale.engine.parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.dedale.engine.execution.Command;

public class ParseConfiguration {
    
    private Map<String, CommandParser> commandsByName = new HashMap<>();
    
    Optional<CommandParser> getParser(String commandName) {
        return Optional.ofNullable(commandsByName.get(commandName));
    }
    
    public void bind(String argument, Supplier<Command> command) {
        commandsByName.put(argument, new CommandParser(command));
    }
    
    public void bind(String argument, Supplier<Command> command, ArgumentProvider argumentProvider) {
        commandsByName.put(argument, new CommandParser(command, argumentProvider));
    }
}
