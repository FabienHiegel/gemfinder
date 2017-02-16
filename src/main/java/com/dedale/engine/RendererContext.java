package com.dedale.engine;

public class RendererContext {
    
    public final StringBuilder out;
    public final StringBuilder err;
    
    public RendererContext() {
        this(new StringBuilder(), new StringBuilder());
    }
    
    public RendererContext(StringBuilder out, StringBuilder err) {
        this.out = out;
        this.err = err;
    }
    
    public String getOutput() {
        if (failed()) {
            return err.toString();
        }
        return out.toString();
    }
    
    public boolean failed() {
        return err.length() > 0;
    }
    
}