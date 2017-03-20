package com.dedale.engine;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.ExecutionContext;
import com.dedale.engine.parsing.ParseConfiguration;
import com.dedale.engine.parsing.ParseEngine;
import com.dedale.engine.renderer.RendererContext;

public class InterpreterEngine {
    
    /**
     * Bad practice
     */
    public static final class keywords {
        public static final String EXIT = "exit";
    }
    
    private Supplier<String> in;
    private Consumer<String> out;
    private ParseConfiguration parseConfiguration;
    
    public InterpreterEngine(Supplier<String> in, Consumer<String> out) {
        this.in = in;
        this.out = out;
        this.parseConfiguration = new ParseConfiguration();
    }
    
    public void run() {
        String commandLine = nextCommandLine();
        EngineContext engineContext = createContext(commandLine);
        runLine(engineContext);
    }
    
    private String nextCommandLine() {
        return in.get();
    }
    
    protected EngineContext createContext(String commandLine) {
        return new EngineContext(commandLine);
    }
    
    private void runLine(EngineContext engineContext) {
        Command command = parseCommand(engineContext);
        executeCommand(engineContext, command);
        render(engineContext);
    }
    
    // Parse
    
    private Command parseCommand(EngineContext engineContext) {
        ParseEngine parseEngine = createParseEngine();
        return parseEngine.parseCommand(engineContext.getParseContext());
    }
    
    protected ParseEngine createParseEngine() {
        return new ParseEngine(parseConfiguration);
    }
    
    public void bind(String argument, Supplier<Command> command) {
        parseConfiguration.bind(argument, command);
    }
    
    // Execute
    
    private void executeCommand(EngineContext engineContext, Command command) {
        ExecutionContext context = engineContext.getExecutionContext();
        command.execute(context);
    }
    
    // Render
    
    private void render(EngineContext engineContext) {
        RendererContext rendererContext = engineContext.getRendereContext();
        out.accept(rendererContext.getOutput());
    }
    
}
