package com.dedale.engine.execution;

import com.dedale.engine.execution.eval.Expression;

public class CommandExpressionArgument<T> implements CommandArgument<T> {
    
    private Expression<T> expression;
    private String name;
    
    public CommandExpressionArgument(String name, Expression<T> delegateExpression) {
        this.name = name;
        this.expression = delegateExpression;
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
