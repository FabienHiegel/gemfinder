package com.dedale.engine.execution.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dedale.engine.execution.expression.ConstantExpression;
import com.dedale.engine.execution.expression.Decorator;

public class JavaneseStdOut extends StdOut {
    
    static final Decorator<String> javanese = JavaneseStdOut::javanese;
    
    public JavaneseStdOut() {
        super(new ConstantExpression<>(""), javanese);
    }
    
    static String javanese(String arg) {
        Matcher vowelsMatcher = Pattern.compile("([aeiouy])").matcher(arg);
        StringBuffer sb = new StringBuffer();
        while (vowelsMatcher.find()) {
            vowelsMatcher.appendReplacement(sb, "av" + vowelsMatcher.group());
        }
        vowelsMatcher.appendTail(sb);
        
        return sb.toString();
    }
    
}
