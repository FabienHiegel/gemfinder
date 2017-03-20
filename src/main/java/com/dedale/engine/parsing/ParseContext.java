package com.dedale.engine.parsing;

import com.dedale.engine.EngineContext;

public class ParseContext {
    public static final String EOL = "__EOL__";
    private static final String TOKEN_SEPARATOR = " ";
    
    private EngineContext engineContext;
    private String args;
    private String commandName;
    
    public ParseContext(EngineContext engineContext, String args) {
        this.engineContext = engineContext;
        this.args = args;
    }
    
    EngineContext getEngineContext() {
        return engineContext;
    }
    
    boolean isEmpty() {
        return args.isEmpty() || EOL.equals(args);
    }
    
    String command() {
        if (commandName == null) {
            commandName = pollToken();
        }
        return commandName;
    }
    
    String nextArgument() {
        return pollToken();
    }
    
    private String pollToken() {
        String[] splittedArguments = args.split(TOKEN_SEPARATOR, 2);
        
        args = splittedArguments.length > 1 ? splittedArguments[1] : EOL;
        return splittedArguments[0];
    }
    
}
