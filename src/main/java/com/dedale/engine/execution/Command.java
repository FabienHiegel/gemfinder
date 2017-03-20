package com.dedale.engine.execution;

public interface Command {
    
    void execute(ExecutionContext context);
    
    Command accept(ExecutionContext context, CommandArgument argument);
    
}
