package com.dedale.engine.execution.eval;

import com.dedale.engine.execution.ExecutionContext;
import com.dedale.engine.execution.eval.ComposedExpression.ExpressionOperation;

@FunctionalInterface
public interface Expression<T> {
    
    T evaluate(ExecutionContext executionContext);
    
    default Expression<T> andThen(ExpressionOperation<T> operation, Expression<T> expression) {
        return operation.of(this, expression);
    }
    
}
