package com.dedale.engine.execution.expression;

import com.dedale.engine.execution.ExecutionContext;

public class ConstantExpression<T> implements Expression<T> {
    
    private T constant;
    
    public ConstantExpression(T constant) {
        this.constant = constant;
    }
    
    @Override
    public T evaluate(ExecutionContext context) {
        return constant;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getClass().getSimpleName());
        s.append('{');
        s.append("constant=").append(constant);
        s.append('}');
        return s.toString();
    }
    
}
