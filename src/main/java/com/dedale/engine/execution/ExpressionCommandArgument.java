package com.dedale.engine.execution;

import com.dedale.engine.execution.expression.Expression;

public class ExpressionCommandArgument<T> implements CommandArgument<T> {
    
    private Expression<T> expression;
    private String name;
    
    public ExpressionCommandArgument(String name, Expression<T> expression) {
        this.name = name;
        this.expression = expression;
    }
    
    @Override
    public String name() {
        return name;
    }
    
    @Override
    public T evaluate(ExecutionContext executionContext) {
        return expression.evaluate(executionContext);
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getClass().getSimpleName());
        s.append('{');
        s.append("name=").append(name);
        s.append(", expression=").append(expression);
        s.append('}');
        return s.toString();
    }
    
}
