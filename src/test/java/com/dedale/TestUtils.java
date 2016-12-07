package com.dedale;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class TestUtils {
    
    public static String getResourceFile(Class<?> seedClazz, String filePath) {
        try {
            URL resource = seedClazz.getResource("");
            File file = new File(resource.getPath(), filePath);
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot exctact file content. " + "seedClazz:\"" + seedClazz + "\"" + ", " + "filePath:\"" + filePath + "\"";
        }
    }
}
