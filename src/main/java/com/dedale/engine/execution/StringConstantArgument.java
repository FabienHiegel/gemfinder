package com.dedale.engine.execution;

import com.dedale.engine.execution.expression.ConstantExpression;

public class StringConstantArgument extends ExpressionCommandArgument<String> implements CommandArgument<String> {
    
    public StringConstantArgument(String name, String constant) {
        super(name, new ConstantExpression<>(constant));
    }
    
}
