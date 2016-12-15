package com.dedale;

import com.dedale.console.ConsoleIO;

public class GemfinderApp {
    
    private boolean running;
    private ConsoleIO consoleIO;
    private String appTitle;
    
    GemfinderApp(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
        this.appTitle = "# Gemfinder App";
    }
    
    public void start() {
        consoleIO.appendln(appTitle).ln();
        running = true;
        while (isRunning()) {
            run();
        }
    }
    
    public void stop() {
        running = false;
    }
    
    void run() {
        String commandLine = consoleIO.nextCommandLine();
        
//        String commandLine = input.nextCommandLine();
//        AppCommand command = commandMapper.parse(commandLine);
//        AppResult result = controller.execute(command);
//        output.consume(result);
        consoleIO.appendln(commandLine);
    }
    
    boolean isRunning() {
        return running;
    }
    
    
}