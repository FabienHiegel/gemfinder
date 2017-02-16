package com.dedale.engine.console;

import com.dedale.engine.InterpreterEngine;

public class ConsoleInterpreterEngine extends InterpreterEngine {
    
    public ConsoleInterpreterEngine(ConsoleIO io) {
        super(io::nextCommandLine, io::appendln);
    }
    
}
