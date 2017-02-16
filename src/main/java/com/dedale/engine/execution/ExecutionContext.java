package com.dedale.engine.execution;

import com.dedale.engine.EngineContext;
import com.dedale.engine.RendererContext;

public class ExecutionContext {
    
    private EngineContext engineContext;
    private  RendererContext rendererContext = new RendererContext();

    public ExecutionContext(EngineContext engineContext) {
        this.engineContext = engineContext;
    }
    
    public RendererContext getRendererContext() {
        return rendererContext;
    }
    
}
