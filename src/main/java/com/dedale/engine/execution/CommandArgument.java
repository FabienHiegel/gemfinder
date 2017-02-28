package com.dedale.engine.execution;

import com.dedale.engine.execution.expression.Expression;

public interface CommandArgument<T> extends Expression<T> {
    
    String name();
    
}
