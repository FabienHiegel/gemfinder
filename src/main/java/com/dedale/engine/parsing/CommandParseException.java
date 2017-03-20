package com.dedale.engine.parsing;

public class CommandParseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public CommandParseException(String message, Exception e) {
        super(message, e);
    }
    
}
