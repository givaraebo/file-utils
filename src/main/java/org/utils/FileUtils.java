package org.utils;

import org.config.CProperties;

public interface FileUtils {

    boolean writeToFile(String fileName, String content);
    String readFromFile(String fileName);

    CProperties getProperties();

    boolean setProperties(String inputDir, String outputDir);
}
