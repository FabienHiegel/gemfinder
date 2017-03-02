package com.dedale.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.ExecutionContext;
import com.dedale.engine.execution.StringConstantArgument;
import com.dedale.engine.execution.core.NoOperation;
import com.dedale.engine.execution.core.StdError;

public class InterpreterEngine {
    
    /**
     * Bad practice
     */
    public static final class keywords {
        public static final String EXIT = "exit";
    }
    
    private Supplier<String> in;
    private Consumer<String> out;
    private Map<String, Supplier<Command>> commandsByName = new HashMap<>();
    
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
        RendererContext rendererContext = runLine(commandLine);
        render(rendererContext.getOutput());
    }

    protected RendererContext runLine(String commandLine) {
        EngineContext engineContext = createContext(commandLine);
        Command command = parseCommand(engineContext);
        return executeCommand(engineContext, command);
    }

    private EngineContext createContext(String commandLine) {
        return new EngineContext(commandLine);
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
        EngineParser parser = engineContext.parser;
        if (parser.isEmpty()) {
            return new NoOperation();
        }
        ExecutionContext executionContext = engineContext.getExecutionContext();
        
        String cmd = parser.nextCommand();
        if (commandsByName.containsKey(cmd)) {
            Command command = commandsByName.get(cmd).get();
            
            command = applyArguments(executionContext, command, parser);
            return command;
        }
        StdError stdError = new StdError();
        stdError.accept(executionContext, new StringConstantArgument("commandName", cmd));
        stdError.accept(executionContext, new StringConstantArgument("errorMessage", ": command not found"));
        return stdError;
    }
    
    private Command applyArguments(ExecutionContext executionContext, Command command, EngineParser parser) {
        if (parser.isEmpty()) {
            return command;
        }
        CommandArgument<?> argument = parser.nextArgument();
        Command arguedCommand = command.accept(executionContext, argument);
        
        return applyArguments(executionContext, arguedCommand, parser);
    }
    
    public void bind(String argument, Supplier<Command> command) {
        commandsByName.put(argument, command);
    }
    
}
