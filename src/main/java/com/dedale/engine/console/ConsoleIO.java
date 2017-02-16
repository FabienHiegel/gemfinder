package com.dedale.engine.console;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIO {
    private static final String DEFAULT_INPUT_PREFIX = "> ";
    private static final String DEFAULT_PROCESSING_MESSAGE = "... processing";
    
    public final Scanner in;
    public final PrintStream out;
    private String inputPrefix;
    private String processingMessage;
    
    public ConsoleIO() {
        this(System.in, System.out, DEFAULT_INPUT_PREFIX, DEFAULT_PROCESSING_MESSAGE);
    }
    
    public ConsoleIO(InputStream in, OutputStream out, String consoleInputPrefix, String processingMessage) {
        this(new Scanner(in), new PrintStream(out), consoleInputPrefix, processingMessage);
    }
    
    private ConsoleIO(Scanner in, PrintStream out, String inputPrefix, String processingMessage) {
        this.in = in;
        this.out = out;
        this.inputPrefix = inputPrefix;
        this.processingMessage = processingMessage;
    }
    
    // Reader
    
    public String nextCommandLine() {
        append(inputPrefix);
        String nextLine = in.nextLine();
        appendln(processingMessage);
        return nextLine;
    }
    
    // Writer
    
    public ConsoleIO append(String str) {
        if (isEmpty(str)) {
            return this;
        }
        out.print(str);
        return this;
    }
    
    public ConsoleIO ln() {
        out.println("");
        return this;
    }
    
    public ConsoleIO appendln(String str) {
        if (isEmpty(str)) {
            return this;
        }
        return append(str).ln();
    }
    
}
