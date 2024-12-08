package org.utils;

import org.config.CProperties;
import org.config.CPropertiesImpl;
import org.exceptions.OperationException;

import java.io.File;
import java.io.FileWriter;

public class FileUtilsImpl implements FileUtils {
    private final CProperties properties = new CPropertiesImpl();

    public FileUtilsImpl() {
        // default constructor

    }

    @Override
    public boolean writeToFile(String fileName, String content) {
        try {
            FileWriter myWriter = new FileWriter(properties.getOutputDir() + fileName);
            myWriter.write(content);
            myWriter.close();
            return true;
        } catch (java.io.IOException e) {
            throw new OperationException("[Class: " + getClass().getSimpleName() + "] " + "Error writing to file: " + fileName,
                    " try to check if the file exists, is not in use or the path is correct"
            );
        }
    }


    @Override
    public String readFromFile(String fileName) {
        try {
            File myObj = new File(properties.getInputDir() + fileName);
            java.util.Scanner myReader = new java.util.Scanner(myObj);
            StringBuilder content = new StringBuilder();
            while (myReader.hasNextLine()) {
                content.append(myReader.nextLine());
            }
            myReader.close();
            return content.toString();
        } catch (java.io.FileNotFoundException e) {
            throw new OperationException("[Class: " + getClass().getSimpleName() + "] " + "Error reading from file: " + fileName,
                    " try to check if the file exists or the path is correct"
            );
        }

    }
}
