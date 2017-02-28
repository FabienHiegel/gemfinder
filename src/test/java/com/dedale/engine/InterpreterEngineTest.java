package com.dedale.engine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.dedale.engine.execution.core.JavaneseStdOut;
import com.dedale.engine.execution.core.ReverseStdOut;
import com.dedale.engine.execution.core.StdOut;

public class InterpreterEngineTest {
    
    private StringBuilder in = new StringBuilder();
    private StringBuilder out = new StringBuilder();
    private InterpreterEngine engine;
    
    @Before
    public void initilizeInterpreter() {
        engine = new InterpreterEngine(() -> {
            String re = in.toString();
            in = new StringBuilder();
            return re;
        }, str -> out.append(str));
        engine.bind("echo", StdOut::new);
        engine.bind("javanese", JavaneseStdOut::new);
        engine.bind("reverse", ReverseStdOut::new);
    }
    
    @Test
    public void should_do_nothing_when_nothing_to_do() throws Exception {
        // When
        engine.run();
        
        // Then
        assertThat(out).isEmpty();
    }
    
    @Test
    public void should_print_error_message_when_command_not_found() throws Exception {
        String output = runLine("foo");
        assertThat(output).isEqualTo("foo: command not found");
    }
    
    @Test
    public void should_print_error_message_when_command_with_args_not_found() throws Exception {
        String output = runLine("foo with some arguments");
        assertThat(output).isEqualTo("foo: command not found");
    }
    
    @Test
    public void should_echo_prints_nothing() throws Exception {
        String output = runLine("echo");
        assertThat(output).isEmpty();
    }
    
    @Test
    public void should_echo_prints_input() throws Exception {
        String output = runLine("echo input");
        assertThat(output).isEqualTo("input");
    }
    
    @Test
    public void should_echo_prints_some_arguments() throws Exception {
        String output = runLine("echo some arguments");
        assertThat(output).isEqualTo("some arguments");
    }
    
    @Test
    public void should_javanese_prints_empty() throws Exception {
        String output = runLine("javanese");
        assertThat(output).isEmpty();
    }
    
    @Test
    public void should_javanese_prints_javanesed_input() throws Exception {
        String output = runLine("javanese input");
        assertThat(output).isEqualTo("avinpavut");
    }
    
    @Test
    public void should_reverse_prints_empty() throws Exception {
        String output = runLine("reverse");
        assertThat(output).isEmpty();
    }
    
    @Test
    public void should_reverse_prints_tupni() throws Exception {
        String output = runLine("reverse input");
        assertThat(output).isEqualTo("tupni");
    }
    
    @Test
    public void should_reverse_reverse_input_prints_input() throws Exception {
        String output = runLine("reverse reverse input");
        assertThat(output).isEqualTo("input");
    }
    
    @Test
    public void should_run_echo_with_r_argument_then_prints_reversed_input() throws Exception {
        String output = runLine("echo -r input");
        assertThat(output).isEqualTo("tupni");
    }
    
    @Test
    public void should_run_echo_with_j_argument_then_prints_javanesed_input() throws Exception {
        String output = runLine("echo -j input");
        assertThat(output).isEqualTo("avinpavut");
    }
    
    @Test
    public void should_run_echo_with_j_r_arguments_then_prints_reversed_then_javanesed_input() throws Exception {
        String output = runLine("echo -j -r input");
        assertThat(output).isEqualTo("tavupnavi");
    }
    
    @Test
    public void should_run_echo_with_r_j_arguments_then_prints_javanesed_then_reversed_input() throws Exception {
        String output = runLine("echo -r -j input");
        assertThat(output).isEqualTo("tuvapniva");
    }
    
    @Test
    public void should_echo_twice_prints_input_on_two_lines() throws Exception {
        String output = runLine("echo input");
        String output2 = runLine("echo second input");
        
        assertThat(output).isEqualTo("input");
        assertThat(output2).isEqualTo("second input");
    }
    
    private String runLine(String line) {
        in.append(line);
        engine.run();
        return output();
    }
    
    private String output() {
        String string = out.toString();
        out = new StringBuilder();
        return string;
    }
}
