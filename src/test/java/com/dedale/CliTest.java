package com.dedale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

public class CliTest {
    
    private Interpreter interpreter;
    
    @Before
    public void initilizeInterpreter() {
        interpreter = new Interpreter();
    }
    
    @Test
    public void should_prints_empty() throws Exception {
        // When
        String output = interpreter.interpret("");
        
        // Then
        assertThat(output).isEmpty();
    }
    
    @Test
    public void should_echo_prints_empty() throws Exception {
        // When
        String output = interpreter.interpret("echo");
        
        // Then
        assertThat(output).isEmpty();
    }
    
    @Test
    public void should_unknown_command_prints_error_message() throws Exception {
        // When
        String output = interpreter.interpret("foo");
        
        // Then
        assertThat(output).isEqualTo("foo: command not found\n");
    }
    
    @Test
    public void should_echo_prints_input() throws Exception {
        // When
        String output = interpreter.interpret("echo input");
        
        // Then
        assertThat(output).isEqualTo("input");
    }
    
    @Test
    public void should_unknown_command_with_args_prints_error_message() throws Exception {
        // When
        String output = interpreter.interpret("foo input");
        
        // Then
        assertThat(output).isEqualTo("foo: command not found\n");
    }
    
    @Test
    @Ignore("Not a test to do now")
    public void should_echo_prints_simple_quoted_input() throws Exception {
        // When
        String output = interpreter.interpret("echo 'input'");
        
        // Then
        assertThat(output).isEqualTo("input");
    }
    
    @Test
    public void should_javanese_prints_empty() throws Exception {
        // When
        String output = interpreter.interpret("javanese");
        
        // Then
        assertThat(output).isEmpty();
    }
    
    @Test
    public void should_javanese_prints_javanesed_input() throws Exception {
        // When
        String output = interpreter.interpret("javanese input");
        
        // Then
        assertThat(output).isEqualTo("avinpavut");
    }
    
    @Test
    public void should_reverse_prints_tupni() throws Exception {
        // When
        String output = interpreter.interpret("reverse input");
        
        // Then
        assertThat(output).isEqualTo("tupni");
    }
    
    // @Test
    // public void should_bind_new_function_allow_to_use_a_new_command() throws Exception {
    // // Given
    // interpreter.bind("foo", arg -> "New function doesnt care about arg");
    //
    // // When
    // String output = interpreter.interpret("foo");
    //
    // // Then
    // assertThat(output).isEqualTo("New function doesnt care about arg");
    // }
    
    @Test
    public void should_reverse_quote_reverse_input_quote_prints_input() throws Exception {
        // When
        String output = interpreter.interpret("reverse `reverse input`");
        
        // Then
        assertThat(output).isEqualTo("input");
    }
    
    @Test
    public void should_reverse_reverse_input_prints_input() throws Exception {
        // When
        String output = interpreter.interpret("reverse reverse input");
        
        // Then
        assertThat(output).isEqualTo("input");
    }
    
    @Test
    public void should_calls_two_sub_command_append_both_results_into_arg_of_main_command() throws Exception {
        // When
        String output = interpreter.interpret("reverse `reverse input` `reverse input`");
        
        // Then
        assertThat(output).isEqualTo("input input");
    }
    
    @Test
    public void when_call_two_unknown_commands_should_print_sub_command_error_first() throws Exception {
        // When
        String output = interpreter.interpret("boo `ghe qsdwfqs` test");
        
        // Then
        assertThat(output).isEqualTo("ghe: command not found\nboo: command not found\n");
    }
    
    @Test
    public void should_echo_with_reversed_alias_prints_reversed_input() throws Exception {
        // When
        String output = interpreter.interpret("echo -r input");
        
        // Then
        assertThat(output).isEqualTo("tupni");
    }
    
    @Test
    public void should_echo_with_javanese_alias_prints_javanesed_input() throws Exception {
        // When
        String output = interpreter.interpret("echo -j input");
        
        // Then
        assertThat(output).isEqualTo("avinpavut");
    }
    
    @Test
    public void should_echo_with_javanese_and_reverse_alias_prints_javanesed_reversed_input() throws Exception {
        // When
        String output = interpreter.interpret("echo -j -r input");
        String expected = interpreter.interpret("javanese `reverse input`");
        
        // Then
        assertThat(output).isEqualTo(expected);
        assertThat(output).isEqualTo("tavupnavi");
    }
    
    @Test
    public void should_echo_with_reverse_then_javanese_alias_prints_reversed_javanesed_input() throws Exception {
        // When
        String output = interpreter.interpret("echo -r -j input");
        String expected = interpreter.interpret("reverse `javanese input`");
        
        // Then
        assertThat(output).isEqualTo(expected);
        assertThat(output).isEqualTo("tuvapniva");
    }
    
    @Test
    public void should_reverse_with_javanese_alias_print_an_error_message() throws Exception {
        // When
        String output = interpreter.interpret("reverse -j input");
        
        // Then
        assertThat(output).isEqualTo("Unknown option: -j");
    }
    
    private static class Interpreter{

        public String interpret(String string) {
            return "Sample";
        }
        
    }
    
}