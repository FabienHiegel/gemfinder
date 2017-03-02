package com.dedale.engine;

import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.StringConstantArgument;

public class EngineParser {
    
    private static final String TOKEN_SEPARATOR = " ";
    private static final String __EOL__ = "__EOL__";
    private String args;
    private String cmd;
    
    public EngineParser(String args) {
        this.args = args;
    }
    
    public boolean isEmpty() {
        return args.isEmpty() || __EOL__.equals(args);
    }
    
    public String nextCommand() {
        if (cmd == null) {
            cmd = pollToken();
        }
        return cmd;
    }
    
    public CommandArgument<?> nextArgument() {
        String token = pollToken();
        return new StringConstantArgument(token, token);
    }
    
    private String pollToken() {
        String[] splittedArguments = args.split(TOKEN_SEPARATOR, 2);
        args = splittedArguments.length > 1 ? splittedArguments[1] : __EOL__;
        return splittedArguments[0];
    }
    
}
