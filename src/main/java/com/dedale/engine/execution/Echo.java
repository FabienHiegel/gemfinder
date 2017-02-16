package com.dedale.engine.execution;

public class Echo implements Command {
    
    @Override
    public void execute(ExecutionContext context) {
        context.getRendererContext().out.append("first");
    }
    
}
