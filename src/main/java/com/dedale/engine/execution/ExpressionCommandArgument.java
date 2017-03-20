package com.dedale.engine.execution;

import com.dedale.engine.execution.expression.Expression;

public class ExpressionCommandArgument<V> implements CommandArgument {
    
    private Expression<V> expression;
    private String name;
    
    public ExpressionCommandArgument(String name, Expression<V> expression) {
        this.name = name;
        this.expression = expression;
    }
    
    @Override
    public String name() {
        return name;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public V evaluate(ExecutionContext executionContext) {
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
