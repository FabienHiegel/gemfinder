package com.dedale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.dedale.engine.console.ConsoleIO;
import com.dedale.engine.console.ConsoleInterpreterEngine;
import com.dedale.engine.console.StringBuilderInputStream;
import com.dedale.engine.console.StringBuilderOutputStream;
import com.dedale.engine.execution.Echo;

public class GemfinderAppTest {
    
    private GemfinderApp app;
    
    private StringBuilderInputStream in;
    private StringBuilderOutputStream out;
    
    private ConsoleInterpreterEngine engine;
    
    @Before
    public void initalize() {
        in = new StringBuilderInputStream();
        out = new StringBuilderOutputStream();
        
        ConsoleIO consoleIO = new ConsoleIO(in, out, "", "");
        engine = new ConsoleInterpreterEngine(consoleIO);
        
        app = new GemfinderApp();
        app.engine(engine);
    }
    
    @Test(timeout = 1000)
    public void should_start_and_stop_app_before_timeout() throws Exception {
        // When
        in.appendln("exit");
        app.start();
        
        // Assert
        assertThat(out.getOutput()).isEqualTo("Exit Gemfinder App\r\n");
    }
    
    @Test
    public void should_unknown_command_prints_error_message() throws Exception {
        // When
        runCommandLine("foo");
        
        // Then
        assertThat(out.getOutput()).isEqualTo("foo: command not found" + System.lineSeparator());
    }
    
    @Test
    public void echo_command_should_print_given_value() throws Exception {
        // Given
        engine.bind("echo", new Echo());
        
        // When
        runCommandLine("echo first");
        
        // Assert
        assertThat(out.getOutput()).isEqualTo("first" + System.lineSeparator());
    }
    
    // Utilitaires
    
    private void runCommandLine(String commandLine) {
        in.appendln(commandLine);
        app.run();
    }
    
}
