package com.dedale.engine.execution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaneseStdOut extends StdOut {
    
    public static String javanese(String arg) {
        Matcher vowelsMatcher = Pattern.compile("([aeiouy])").matcher(arg);
        StringBuffer sb = new StringBuffer();
        while (vowelsMatcher.find()) {
            vowelsMatcher.appendReplacement(sb, "av" + vowelsMatcher.group());
        }
        vowelsMatcher.appendTail(sb);
        
        return sb.toString();
    }
    
    @Override
    protected String value(ExecutionContext engineContext) {
        return javanese(super.value(engineContext));
    }
    
}
