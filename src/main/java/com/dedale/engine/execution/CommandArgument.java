package com.dedale.engine.execution;

public interface CommandArgument {
    
    String name();
    
    <T> T evaluate(ExecutionContext executionContext);
}
