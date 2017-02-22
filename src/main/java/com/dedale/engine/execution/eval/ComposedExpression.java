package com.dedale.engine.execution.eval;

import com.dedale.engine.execution.ExecutionContext;

public class ComposedExpression<T> implements Expression<T> {
    
    private Expression<T> exp1;
    private Expression<T> exp2;
    private ExpressionOperation<T> operation;
    
    public ComposedExpression(ExpressionOperation<T> op, Expression<T> exp1, Expression<T> exp2) {
        this.operation = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    
    @Override
    public T evaluate(ExecutionContext executionContext) {
        return operation.operate(exp1.evaluate(executionContext), exp2.evaluate(executionContext));
    }
    
    @Override
    public ComposedExpression<T> andThen(ExpressionOperation<T> operation, Expression<T> after) {
        return new ComposedExpression<>(operation, this, after);
    }
    
    @FunctionalInterface
    public static interface ExpressionOperation<T> {
        T operate(T first, T second);
        
        default ComposedExpression<T> of(Expression<T> exp1, Expression<T> exp2) {
            return new ComposedExpression<>(this, exp1, exp2);
        }
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
