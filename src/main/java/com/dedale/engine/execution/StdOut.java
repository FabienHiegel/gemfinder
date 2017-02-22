package com.dedale.engine.execution;

import com.dedale.engine.execution.eval.ComposedExpression.ExpressionOperation;
import com.dedale.engine.execution.eval.ConstantExpression;
import com.dedale.engine.execution.eval.Expression;
import com.dedale.engine.execution.eval.ExpressionDecorator.Decorator;

public class StdOut implements Command {
    
    protected Expression<String> expression = new ConstantExpression<>("");
    
    @Override
    public void execute(ExecutionContext context) {
        String trim = value(context);
        context.getRendererContext().out.append(trim);
    }
    
    protected String value(ExecutionContext engineContext) {
        return expression == null ? "" : expression.evaluate(engineContext);
    }
    
    @Override
    public <T> Command accept(ExecutionContext context, CommandArgument<T> argument) {
        String argumentName = argument.name();
        Expression<String> stringArgument = (Expression<String>) argument;
        switch (argumentName) {
            case "-r":
            case "reverse":
                expression = reverse.decorate(expression);
                break;
            case "-j":
            case "javanese":
                expression = javanese.decorate(expression);
                break;
            default:
                expression = appendExpressions.of(stringArgument, expression);
                break;
        }
        return this;
    }
    
    protected static final Decorator<String> reverse = ReverseStdOut::reverse;
    protected static final Decorator<String> javanese = JavaneseStdOut::javanese;
    protected static final ExpressionOperation<String> appendExpressions = StdOut::appendExpressions;
    
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
