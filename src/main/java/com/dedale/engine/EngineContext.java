package com.dedale.engine;

import com.dedale.engine.execution.ExecutionContext;

public class EngineContext {
    
    String commandLine;
    private ExecutionContext executionContext = new ExecutionContext(this);
    
    public EngineContext(String commandLine) {
        this.commandLine = commandLine;
    }
    
    public ExecutionContext getExecutionContext() {
        return executionContext;
    }
    
}
