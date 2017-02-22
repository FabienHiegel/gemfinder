package com.dedale.engine.execution;

import com.dedale.engine.execution.eval.Expression;

public interface CommandArgument<T> extends Expression<T> {
    
    String name();
    
}
