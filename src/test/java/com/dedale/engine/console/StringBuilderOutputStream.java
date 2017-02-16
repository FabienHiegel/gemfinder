package com.dedale.engine.console;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Give ability to forge an output String, in scope tests.
 */
public final class StringBuilderOutputStream extends OutputStream {
    private final StringBuilder builder = new StringBuilder();
    
    public void write(int _byte) throws IOException {
        builder.append((char) _byte);
    }
    
    public String getOutput(){
        return builder.toString();
    }
}