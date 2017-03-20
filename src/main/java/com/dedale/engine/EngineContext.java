package com.dedale.engine;

import com.dedale.engine.execution.ExecutionContext;
import com.dedale.engine.parsing.ParseContext;
import com.dedale.engine.renderer.RendererContext;

public class EngineContext {
    
    private ParseContext parsingContext;
    private ExecutionContext executionContext;
    private RendererContext rendererContext = new RendererContext();
    
    public EngineContext(String commandLine) {
        this.executionContext = new ExecutionContext(rendererContext);
        this.parsingContext = new ParseContext(this, commandLine);
    }
    
    public ParseContext getParseContext() {
        return parsingContext;
    }
    
    public ExecutionContext getExecutionContext() {
        return executionContext;
    }
    
    
    public RendererContext getRendereContext() {
        return rendererContext;
    }
    
}
