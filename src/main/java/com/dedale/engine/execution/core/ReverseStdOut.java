package com.dedale.engine.execution.core;

import com.dedale.engine.execution.expression.ConstantExpression;
import com.dedale.engine.execution.expression.Decorator;

public class ReverseStdOut extends StdOut {
    
    static final Decorator<String> reverse = ReverseStdOut::reverse;
    
    public ReverseStdOut() {
        super(new ConstantExpression<>(""), reverse);
    }
    
    static String reverse(String arg) {
        return new StringBuilder(arg).reverse().toString();
    }
    
}
