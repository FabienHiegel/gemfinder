package com.dedale.engine.execution;

public interface Command {
    
    void execute(ExecutionContext context);
    
    <T> Command accept(ExecutionContext context, CommandArgument<T> argument);
    
}
