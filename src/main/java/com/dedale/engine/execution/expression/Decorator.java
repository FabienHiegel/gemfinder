package com.dedale.engine.execution.expression;

@FunctionalInterface
public interface Decorator<T> {
    T decorate(T exp);
    
    default ExpressionDecorator<T> decorate(Expression<T> expression) {
        return new ExpressionDecorator<>(this, expression);
    }
    
    default Decorator<T> compose(Decorator<T> before) {
        return t -> this.decorate(before.decorate(t));
    }
    
    default Decorator<T> andThen(Decorator<T> after) {
        return t -> after.decorate(this.decorate(t));
    }
}