package com.dedale.engine.execution.expression;

@FunctionalInterface
public interface Operation<T> {
    T operate(T first, T second);
    
    default ComposedExpression<T> of(Expression<T> exp1, Expression<T> exp2) {
        return new ComposedExpression<>(this, exp1, exp2);
    }
}