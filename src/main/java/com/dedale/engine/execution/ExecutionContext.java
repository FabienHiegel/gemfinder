package com.dedale.engine.execution;

import com.dedale.engine.renderer.RendererContext;

public class ExecutionContext {
    
    private RendererContext rendererContext;
    
    public ExecutionContext(RendererContext rendererContext) {
        this.rendererContext = rendererContext;
    }
    
    public RendererContext getRendererContext() {
        return rendererContext;
    }
    
}
