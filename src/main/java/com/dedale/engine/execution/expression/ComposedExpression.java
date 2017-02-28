package com.dedale.engine.execution.expression;

import com.dedale.engine.execution.ExecutionContext;

public class ComposedExpression<T> implements Expression<T> {
    
    private Expression<T> exp1;
    private Expression<T> exp2;
    private Operation<T> operation;
    
    public ComposedExpression(Operation<T> op, Expression<T> exp1, Expression<T> exp2) {
        this.operation = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    
    @Override
    public T evaluate(ExecutionContext executionContext) {
        return operation.operate(exp1.evaluate(executionContext), exp2.evaluate(executionContext));
    }
    
    @Override
    public ComposedExpression<T> andThen(Operation<T> operation, Expression<T> after) {
        return new ComposedExpression<>(operation, this, after);
    }
    
    // Object implementation methods
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getClass().getSimpleName());
        s.append('{');
        s.append("operation=").append(operation);
        s.append(", exp1=").append(exp1);
        s.append(", exp2=").append(exp2);
        s.append('}');
        return s.toString();
    }
}
