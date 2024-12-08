package org.config;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface CProperties {
    void loadProperties(String configFileName);

    Object getCProperty(String property);
    boolean setCProperty(String property, String value);
    String getOutputDir();
    String getInputDir();

    File getInputDirFile();

    File getOutputDirFile();

    void setInputDir(File inputDir);

    void setOutputDir(File outputDir);

    File[] getOutputDirFiles();

    File[] getInputDirFiles();

    Map<String, List<File>> filesGroupByFileType(Enum dirTypeEnum);

    List<File> getDirectories(DirType dirTypeEnum);
}
