package com.dedale.engine.parsing;

import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.ConstantArgument;

public class ConstantArgumentParser implements ArgumentProvider {
    
    public CommandArgument nextArgument(ParseContext parseContext) {
        String argument = parseContext.nextArgument();
        return new ConstantArgument<>(argument, argument);
    }
    
}
