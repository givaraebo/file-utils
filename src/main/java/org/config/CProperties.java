package org.config;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface CProperties {

    /**
     * Load the properties from the config file
     * @param configFileName
     */
    void loadProperties(String configFileName);

    /**
     * Get the property value
     * @param property
     * @return
     */
    Object getCProperty(String property);

    /**
     * Set the property value
     * @param property
     * @param value
     * @return
     */
    boolean setCProperty(String property, String value);

    /**
     * Get the output directory
     * @return
     */
    String getOutputDir();


    String getOutputDir(String extension);

    /**
     * Get the input directory
     * @return
     */
    String getInputDir();


    /**
     * Get the input directory file
     * @return
     */
    File getInputDirFile();

    /**
     * Get the output directory file
     * @return
     */
    File getOutputDirFile();

    /**
     * Set the input directory
     * @param inputDir
     */
    void setInputDir(File inputDir);

    /**
     * Set the output directory
     * @param outputDir
     */
    void setOutputDir(File outputDir);

    /**
     * Get list of files in the output directory
     * @return
     */
    File[] getOutputDirFiles();

    /**
     * Get list of files in the input directory
     * @return
     */
    File[] getInputDirFiles();

    /**
     * Get the list of files grouped by file type
     * @param dirTypeEnum
     * @return
     */
    Map<String, List<File>> filesGroupByFileType(Enum dirTypeEnum);

    /**
     * Get the list of directories
     * @param dirTypeEnum
     * @return
     */
    List<File> getDirectories(DirType dirTypeEnum);
}
