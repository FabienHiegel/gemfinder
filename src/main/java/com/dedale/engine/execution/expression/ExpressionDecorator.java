package com.dedale.engine.execution.expression;

import com.dedale.engine.execution.ExecutionContext;

public class ExpressionDecorator<T> implements Expression<T> {
    
    private Expression<T> expression;
    private Decorator<T> decorator;
    
    public ExpressionDecorator(Decorator<T> decorator, Expression<T> expression) {
        this.decorator = decorator;
        this.expression = expression;
    }
    
    @Override
    public T evaluate(ExecutionContext executionContext) {
        return decorator.decorate(expression.evaluate(executionContext));
    }
    
    // Object implementation methods
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getClass().getSimpleName());
        s.append('{');
        s.append("expression=").append(expression);
        s.append(", decorator=").append(decorator);
        s.append('}');
        return s.toString();
    }
    
}
