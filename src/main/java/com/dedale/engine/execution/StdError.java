package com.dedale.engine.execution;

public class StdError implements Command {
    
    private String input = "";
    
    @Override
    public void execute(ExecutionContext context) {
        context.getRendererContext().err.append(input.trim());
    }
    
    @Override
    public <T> Command accept(ExecutionContext context, CommandArgument<T> argument) {
        input += argument.evaluate(context);
        return this;
    }
    
}
