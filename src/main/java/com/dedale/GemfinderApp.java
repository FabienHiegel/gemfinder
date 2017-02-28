package com.dedale;

import java.util.ArrayList;
import java.util.Collection;

import com.dedale.engine.InterpreterEngine;
import com.dedale.engine.console.ConsoleIO;
import com.dedale.engine.console.ConsoleInterpreterEngine;
import com.dedale.engine.execution.core.Exit;
import com.dedale.engine.execution.core.StdOut;

public class GemfinderApp {
    
    private boolean running;
    private String appTitle;
    private Collection<InterpreterEngine> engines = new ArrayList<>();
    
    GemfinderApp() {
        this.appTitle = "Gemfinder App";
    }
    
    public void start() {
        running = true;
        while (isRunning()) {
            run();
        }
    }
    
    public void stop() {
        running = false;
    }
    
    void run() {
        for (InterpreterEngine engine : engines) {
            engine.run();
        }
    }
    
    boolean isRunning() {
        return running;
    }
    
    public String getAppTitle() {
        return appTitle;
    }
    
    public void engine(InterpreterEngine engine) {
        defaultExit(engine);
        engines.add(engine);
    }
    
    private void defaultExit(InterpreterEngine engine) {
        engine.bind(InterpreterEngine.keywords.EXIT, () -> new Exit(this));
    }
    
    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO();
        InterpreterEngine engine = new ConsoleInterpreterEngine(consoleIO);
        engine.bind("echo", StdOut::new);
        
        GemfinderApp app = new GemfinderApp();
        app.engine(engine);
        app.start();
    }
    
}