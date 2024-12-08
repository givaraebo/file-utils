package org.utils;

import org.config.CProperties;
import org.config.CPropertiesImpl;

public class FileUtilsImpl implements FileUtils {
    private CProperties properties = new CPropertiesImpl(getClass());

    public FileUtilsImpl() {
        // default constructor

    }

    public void writeToFile(String content, String path) {
        // write content to file
    }

    public String readFromFile(String path) {
        // read content from file
        return null;
    }
}
