package com.dedale.engine.execution.core;

import static com.dedale.engine.execution.core.JavaneseStdOut.javanese;
import static com.dedale.engine.execution.core.ReverseStdOut.reverse;

import com.dedale.engine.execution.Command;
import com.dedale.engine.execution.CommandArgument;
import com.dedale.engine.execution.ExecutionContext;
import com.dedale.engine.execution.expression.ConstantExpression;
import com.dedale.engine.execution.expression.Decorator;
import com.dedale.engine.execution.expression.Expression;
import com.dedale.engine.execution.expression.Operation;

public class StdOut implements Command {
    
    protected static final Operation<String> appendExpressions = StdOut::appendExpressions;
    
    protected Decorator<String> expressionDecorator;
    protected Expression<String> expression;
    
    public StdOut(Expression<String> expression,  Decorator<String> expressionDecorator) {
        this.expression = expression;
        this.expressionDecorator = expressionDecorator;
    }
    
    public StdOut() {
        this(new ConstantExpression<>(""), expression -> expression);
    }
    
    @Override
    public void execute(ExecutionContext context) {
        String trim = evaluateExpression(context);
        context.getRendererContext().out.append(trim);
    }
    
    protected String evaluateExpression(ExecutionContext engineContext) {
        return expressionDecorator.decorate(expression).evaluate(engineContext);
    }
    
    protected void appendStringExpression(CommandArgument argument) {
        expression = expression.andThen(appendExpressions, argument::evaluate);
    }
    
    @Override
    public Command accept(ExecutionContext context, CommandArgument argument) {
        String argumentName = argument.name();
        switch (argumentName) {
            case "-r":
            case "reverse":
                expressionDecorator = expressionDecorator.compose(reverse);
                return this;
            case "-j":
            case "javanese":
                expressionDecorator = expressionDecorator.compose(javanese);
                return this;
            default:
                appendStringExpression(argument);
                return this;
        }
    }
    
    private static final String appendExpressions(String str1, String str2) {
        if (str1 == null || str1.isEmpty()) {
            return str2;
        }
        if (str2 == null || str2.isEmpty()) {
            return str1;
        }
        return str1 + " " + str2;
    }
    
}
