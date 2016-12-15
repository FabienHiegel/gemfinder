package com.dedale.console;

import java.io.IOException;
import java.io.InputStream;

/**
 * Give ability to forge an input String, in scope tests.
 */
public final class StringBuilderInputStream extends InputStream {
    private StringBuilder builder = new StringBuilder();

    @Override
    public int read() throws IOException {
        if(builder.length() > 0){
            int charCode = builder.codePointAt(0);
            builder.deleteCharAt(0);
            return charCode;
        }
        return -1;
    }
 
    public StringBuilderInputStream append(String str){
        builder.append(str);
        return this;
    }
    public StringBuilderInputStream appendln(String str){
        return append(str).ln();
    }
    
    public StringBuilderInputStream ln() {
        return append(System.lineSeparator());
    }
    
}
