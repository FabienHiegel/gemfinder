package com.dedale.engine;

import com.dedale.engine.execution.ExecutionContext;

public class EngineContext {
    
    private ExecutionContext executionContext = new ExecutionContext(this);
    public EngineParser parser;
    
    public EngineContext(String commandLine) {
        this.parser = new EngineParser(commandLine);
    }
    
    public ExecutionContext getExecutionContext() {
        return executionContext;
    }
    
}
