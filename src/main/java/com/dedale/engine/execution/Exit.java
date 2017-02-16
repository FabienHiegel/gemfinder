package com.dedale.engine.execution;

import com.dedale.GemfinderApp;

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
    
}
