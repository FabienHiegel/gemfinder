package com.dedale.engine.parsing;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.ConstantArgument;
import com.dedale.engine.execution.core.NoOperation;
import com.dedale.engine.execution.core.StdError;

public class ParseEngine {
    
    private ParseConfiguration configuration;
    
    public ParseEngine(ParseConfiguration configuration) {
        this.configuration = configuration;
    }
    
    public Command parseCommand(ParseContext parsingContext) {
        CommandParser commandParser = getCommandParser(parsingContext);
        return commandParser.parseCommand(parsingContext);
    }
    
    private CommandParser getCommandParser(ParseContext parsingContext) {
        if (parsingContext.isEmpty()) {
            return doNothing();
        }
        String commandName = parsingContext.command();
        return configuration.getParser(commandName).orElse(commandNotFound(commandName));
    }
    
    protected CommandParser doNothing() {
        return new CommandParser(NoOperation::new);
    }
    
    protected CommandParser commandNotFound(String commandName) {
        ArgumentProvider argumentParser = new ArgumentProvider() {
            
            private boolean hasNext = true;
            
            @Override
            public boolean hasNext(ParseContext parseContext) {
                return hasNext;
            }
            
            @Override
            public CommandArgument nextArgument(ParseContext parseContext) {
                hasNext = false;
                return new ConstantArgument<>("errorMessage", commandName + ": command not found");
            }
        };
        return new CommandParser(StdError::new, argumentParser);
    }
    
}
