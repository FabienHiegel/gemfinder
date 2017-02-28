package com.dedale.engine.execution.expression;

import com.dedale.engine.execution.ExecutionContext;

@FunctionalInterface
public interface Expression<T> {
    
    T evaluate(ExecutionContext executionContext);
    
    default Expression<T> andThen(Operation<T> operation, Expression<T> expression) {
        return operation.of(this, expression);
    }
    
}
