package com.dedale.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.StringConstantArgument;

public class EngineParser {
    
    private static final String TOKEN_SEPARATOR = " ";
    private static final String __EOL__ = "__EOL__";
    private String args;
    private String cmd;
    
    // private ParserUtils utils = new ParserUtils();
    
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
    
    public String nextToken() {
        String[] splittedArguments = args.split(TOKEN_SEPARATOR, 2);
        return splittedArguments[0];
    }
    
    public String pollToken() {
        String[] splittedArguments = args.split(TOKEN_SEPARATOR, 2);
        args = splittedArguments.length > 1 ? splittedArguments[1] : __EOL__;
        return splittedArguments[0];
    }
    
    public CommandArgument<?> nextArgument() {
        String token = pollToken();
        return new StringConstantArgument(token, token);
    }
    
    public List<CommandArgument<?>> arguments() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        
        List<CommandArgument<?>> arguments = new ArrayList<>();
        arguments.add(nextArgument());
        arguments.addAll(arguments());
        return arguments;
    }
    
}
