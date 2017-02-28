package com.dedale.engine.execution.core;

import com.dedale.GemfinderApp;
import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.ExecutionContext;

public class Exit implements Command {
    
    private GemfinderApp app;
    
    public Exit(GemfinderApp app) {
        this.app = app;
    }
    
    @Override
    public void execute(ExecutionContext context) {
        context.getRendererContext().out.append("Exit ").append(app.getAppTitle());
        app.stop();
    }
    
    @Override
    public <T> Command accept(ExecutionContext context, CommandArgument<T> argument) {
        return this;
    }
    
}
