package org.main;

import org.config.CProperties;
import org.config.CPropertiesImpl;
import org.config.DirType;
import org.utils.FileUtils;
import org.utils.FileUtilsImpl;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtilsImpl();
        fileUtils.writeToFile("test.txt", "Hello World");


    }
}