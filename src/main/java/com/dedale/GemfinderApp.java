package com.dedale;

import java.util.ArrayList;
import java.util.Collection;

import com.dedale.engine.InterpreterEngine;
import com.dedale.engine.execution.Exit;

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
    
    public void engine(InterpreterEngine engine){
        defaultExit(engine);
        engines.add(engine);
    }

    private void defaultExit(InterpreterEngine engine) {
        engine.bind(InterpreterEngine.keywords.EXIT, new Exit(this));
    }
    
}