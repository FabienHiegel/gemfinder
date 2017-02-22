package com.dedale.engine.execution.eval;

import com.dedale.engine.execution.ExecutionContext;
import com.dedale.engine.execution.eval.ComposedExpression.ExpressionOperation;

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
    
    @FunctionalInterface
    public static interface Decorator<T> {
        T decorate(T exp);
        
        default <E extends Expression<T>> ExpressionDecorator<T> decorate(E expression) {
            return new ExpressionDecorator<>(this, expression);
        }
        
        default Decorator<T> compose(Decorator<T> before) {
            return t -> this.decorate(before.decorate(t));
        }
        
    }
    
    @Override
    public ExpressionDecorator<T> andThen(ExpressionOperation<T> operation, Expression<T> expression) {
        this.expression = this.expression.andThen(operation, expression);
        return this;
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
