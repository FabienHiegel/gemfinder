package com.dedale.engine.execution;

public class ReverseStdOut extends StdOut {
    
    static String reverse(String arg) {
        return new StringBuilder(arg).reverse().toString();
    }
    
    @Override
    protected String value(ExecutionContext engineContext) {
        return reverse(super.value(engineContext));
    }
    
}
