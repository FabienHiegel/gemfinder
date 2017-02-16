package com.dedale.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.ExecutionContext;

public class InterpreterEngine {
    
    /**
     * Bad practice
     */
    public static final class keywords {
        public static final String EXIT = "exit";
    }
    
    private Supplier<String> in;
    private Consumer<String> out;
    private Map<String, Command> commandsByName = new HashMap<>();
    
    public InterpreterEngine(Supplier<String> in, Consumer<String> out) {
        this.in = in;
        this.out = out;
    }
    
    private String nextCommandLine() {
        return in.get();
    }
    
    private void render(String output) {
        out.accept(output);
    }
    
    public void run() {
        String commandLine = nextCommandLine();
        
        EngineContext engineContext = new EngineContext(commandLine);
        Command command = parseCommand(engineContext);
        RendererContext context = executeCommand(engineContext, command);
        
        render(context.getOutput());
    }
    
    private RendererContext executeCommand(EngineContext engineContext, Command command) {
        ExecutionContext context = engineContext.getExecutionContext();
        command.execute(context);
        return context.getRendererContext();
    }
    
    //
    // Parsers
    //
    
    public Command parseCommand(EngineContext engineContext) {
        String cmd = engineContext.commandLine.split(" ")[0];
        if (commandsByName.containsKey(cmd)) {
            return commandsByName.get(cmd);
        }
        return new Command() {
            
            @Override
            public void execute(ExecutionContext context) {
                context.getRendererContext().err.append(cmd).append(": command not found");
            }
        };
    }
    
    public void bind(String argument, Command command) {
        commandsByName.put(argument, command);
    }
    
}
