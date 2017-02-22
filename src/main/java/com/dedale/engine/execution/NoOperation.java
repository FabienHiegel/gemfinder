package com.dedale.engine.execution;

public class NoOperation implements Command {
    
    @Override
    public void execute(ExecutionContext context) {
    }
    
    @Override
    public <T> Command accept(ExecutionContext context, CommandArgument<T> argument) {
        return this;
    }
    
}
