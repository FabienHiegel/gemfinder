package com.dedale.engine.execution;

public class ConstantArgument<T> implements CommandArgument {
    
    private String name;
    private T constant;
    
    public ConstantArgument(String name, T constant) {
        this.name = name;
        this.constant = constant;
    }
    
    @Override
    public String name() {
        return name;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T evaluate(ExecutionContext executionContext) {
        return constant;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getClass().getSimpleName());
        s.append('{');
        s.append("name=").append(name);
        s.append(", constant=").append(constant);
        s.append('}');
        return s.toString();
    }
    
}
