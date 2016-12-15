package com.dedale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.dedale.GemfinderApp;
import com.dedale.console.ConsoleIO;
import com.dedale.console.StringBuilderInputStream;
import com.dedale.console.StringBuilderOutputStream;

public class GemfinderAppTest {
    
    private StringBuilderInputStream in;
    private StringBuilderOutputStream out;
    private GemfinderApp app;
    
    @Before
    public void initalize() {
        in = new StringBuilderInputStream();
        out = new StringBuilderOutputStream();
        app = new GemfinderApp(new ConsoleIO(in, out, "", ""));
    }
    
    @Test
    public void skeleton_input_output_tested_once() throws Exception {
        // Arrange
        in.appendln("test");
        
        // Act
        app.run();
        
        // Assert
        assertThat(out.getOutput()).isEqualTo("test\r\n");
    }
    
    
    @Test
    public void skeleton_input_output_tested_three_times() throws Exception {
        // Arrange
        in.appendln("test");
        in.appendln("test");
        in.appendln("test");
        
        // Act
        app.run();
        app.run();
        app.run();
        
        // Assert
        assertThat(out.getOutput()).isEqualTo("test\r\ntest\r\ntest\r\n");
    }
    
}
