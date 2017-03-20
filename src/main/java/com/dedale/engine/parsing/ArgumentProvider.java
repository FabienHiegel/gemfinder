package com.dedale.engine.parsing;

import com.dedale.engine.execution.CommandArgument;

public interface ArgumentProvider {
    
    CommandArgument nextArgument(ParseContext parseContext);
    
    default boolean hasNext(ParseContext parseContext) {
        return !parseContext.isEmpty();
    }
    
}
