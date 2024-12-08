package org.utils;

import org.config.CProperties;
import org.config.CPropertiesImpl;

public class FileUtilsImpl implements FileUtils {
    private CProperties properties = new CPropertiesImpl();

    public FileUtilsImpl() {
        // default constructor
        if (!properties.getInputDirFile().exists()){
            boolean isCreated = properties.getInputDirFile().mkdirs();
            if (isCreated){
                System.err.println("[Class name: "+getClass().getSimpleName()+"]"  +"Input directory created: " + properties.getInputDirFile().getAbsolutePath());
            }else {
                System.err.println("[Class name: "+getClass().getSimpleName()+"]"  +"Input directory creation failed: " + properties.getInputDirFile().getAbsolutePath());
            }
        }
        if (!properties.getOutputDirFile().exists()) {
            boolean isCreated = properties.getOutputDirFile().mkdirs();
            if (isCreated){
                System.err.println("[Class name: "+getClass().getSimpleName()+"]"  +"Output directory created: " + properties.getOutputDirFile().getAbsolutePath());
            }else {
                System.err.println("[Class name: "+getClass().getSimpleName()+"]"  +"Output directory creation failed: " + properties.getOutputDirFile().getAbsolutePath());
            }
        }
    }

    public void writeToFile(String content, String path) {
        // write content to file
    }

    public String readFromFile(String path) {
        // read content from file
        return null;
    }
}
