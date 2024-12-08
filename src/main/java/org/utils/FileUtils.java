package org.utils;

public interface FileUtils {

    boolean writeToFile(String fileName, String content);
    String readFromFile(String fileName);
}
