package com.dedale.engine.console;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIO {
    private static final String DEFAULT_INPUT_PREFIX = "> ";
    
    public final Scanner in;
    public final PrintStream out;
    private String inputPrefix;
    private String processingMessage;
    
    public ConsoleIO() {
        this(System.in, System.out, DEFAULT_INPUT_PREFIX);
    }
    
    public ConsoleIO(InputStream in, OutputStream out, String consoleInputPrefix) {
        this(new Scanner(in), new PrintStream(out), consoleInputPrefix);
    }
    
    private ConsoleIO(Scanner in, PrintStream out, String inputPrefix) {
        this.in = in;
        this.out = out;
        this.inputPrefix = inputPrefix;
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
