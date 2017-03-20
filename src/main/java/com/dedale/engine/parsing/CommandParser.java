package com.dedale.engine.parsing;

import java.util.function.Supplier;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.CommandArgument;

public class CommandParser {
    
    private Supplier<Command> commandFactory;
    private ArgumentProvider argumentProvider;
    
    public CommandParser(Supplier<Command> commandFactory) {
        this(commandFactory, new ConstantArgumentParser());
    }
    
    public CommandParser(Supplier<Command> commandFactory, ArgumentProvider argumentProvider) {
        this.commandFactory = commandFactory;
        this.argumentProvider = argumentProvider;
    }
    
    public Command parseCommand(ParseContext parsingContext) {
        Command command = createCommand();
        
        return applyArguments(parsingContext, command, argumentProvider);
    }
    
    protected Command createCommand() {
        return commandFactory.get();
    }
    
    private Command applyArguments(ParseContext parsingContext, Command command, ArgumentProvider argumentProvider) {
        if (!argumentProvider.hasNext(parsingContext)) {
            return command;
        }
        CommandArgument argument = argumentProvider.nextArgument(parsingContext);
        Command arguedCommand = command.accept(parsingContext.getEngineContext().getExecutionContext(), argument);
        
        return applyArguments(parsingContext, arguedCommand, argumentProvider);
    }
    
}
