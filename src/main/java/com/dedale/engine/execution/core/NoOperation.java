package com.dedale.engine.execution.core;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.ExecutionContext;

public class NoOperation implements Command {
    
    @Override
    public void execute(ExecutionContext context) {
    }
    
    @Override
    public Command accept(ExecutionContext context, CommandArgument argument) {
        return this;
    }
    
}
