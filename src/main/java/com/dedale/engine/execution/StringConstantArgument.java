package com.dedale.engine.execution;

import com.dedale.engine.execution.eval.ConstantExpression;

public class StringConstantArgument extends CommandExpressionArgument<String> implements CommandArgument<String> {
    
    public StringConstantArgument(String name, String constant) {
        super(name, new ConstantExpression<>(constant));
    }
    
}
