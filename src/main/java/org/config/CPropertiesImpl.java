package org.config;

import org.exceptions.InputException;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class is used to provide the properties of the application.
 */
public class CPropertiesImpl extends Properties implements CProperties {
    private File inputDir;
    private File outputDir;
    private String configFileName = "config.properties";

    public CPropertiesImpl(Class<?> clazz) {
        super();
        loadProperties(configFileName);
    }

    public CPropertiesImpl(String configFileName) {
        super();
        if (configFileName == null || configFileName.isEmpty()) {
            throw new IllegalArgumentException( "[Class: "+getClass().getSimpleName()+"] " +"Config file cannot be null or empty");
        }
        this.configFileName = configFileName;
        loadProperties(configFileName);
    }

    public CPropertiesImpl(File inputDir, File outputDir) {
        super();
        if (inputDir == null || outputDir == null) {
            throw new IllegalArgumentException( "[Class: "+getClass().getSimpleName()+"] " +"Input and output directories cannot be null");
        }
        this.inputDir = inputDir;
        this.outputDir = outputDir;
    }

    @Override
    public void loadProperties(String configFileName) {
        try {
            super.load(getClass().getClassLoader().getResourceAsStream(configFileName));
            try {
                inputDir = new File(super.getProperty("inputDir"));
            }catch (Exception e){
                throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +"Input directory not found in config file",
                        " try to add inputDir property in config file"
                );
            }
            try {
                outputDir = new File(super.getProperty("outputDir"));
            }
            catch (Exception e){
                throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +"Output directory not found in config file",
                        " try to add outputDir property in config file"
                );
            }
        } catch (IOException e) {
            throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +" Config file not found: " + configFileName,
                    " try to create a config file with inputDir and outputDir properties in resources directory"
                    );
        }catch (NullPointerException e){
            throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +" Config file in resources directory not found: " + configFileName
                    ," try to create a config file with inputDir and outputDir properties in resources directory"
            );
        }
    }


    private void createDirs() {
        if (!getInputDirFile().exists()){
            boolean isCreated = getInputDirFile().mkdirs();
            if (isCreated){
                System.err.println("[Class: "+getClass().getSimpleName()+"]"  +"Input directory created: " + getInputDirFile().getAbsolutePath());
            }else {
                System.err.println("[Class: "+getClass().getSimpleName()+"]"  +"Input directory creation failed: " + getInputDirFile().getAbsolutePath());
            }
        }
        if (!getOutputDirFile().exists()) {
            boolean isCreated = getOutputDirFile().mkdirs();
            if (isCreated){
                System.err.println("[Class: "+getClass().getSimpleName()+"]"  +"Output directory created: " + getOutputDirFile().getAbsolutePath());
            }else {
                System.err.println("[Class: "+getClass().getSimpleName()+"]"  +"Output directory creation failed: " + getOutputDirFile().getAbsolutePath());
            }
        }
    }

    @Override
    public Object getCProperty(String property) {
        return super.get(property);
    }

    @Override
    public boolean setCProperty(String property, String value) {
        try {
            super.setProperty(property, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getOutputDir() {
        return outputDir.getAbsolutePath();
    }

    @Override
    public String getInputDir() {
        return inputDir.getAbsolutePath();
    }

    @Override
    public File getInputDirFile() {
        return inputDir;
    }

    @Override
    public File getOutputDirFile() {
        return outputDir;
    }

    @Override
    public void setInputDir(File inputDir) {
        this.inputDir = inputDir;
    }

    @Override
    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }


    @Override
    public File[] getOutputDirFiles() {
        return outputDir.listFiles();
    }

    @Override
    public File[] getInputDirFiles() {
        return inputDir.listFiles();
    }


    @Override
    public Map<String, List<File>> filesGroupByFileType(Enum dirTypeEnum) {
        // group by file type
        Map<String, List<File>> map = new HashMap<>();
        List<File> dirFiles = List.of(dirTypeEnum == DirType.INPUT ? getInputDirFiles() : getOutputDirFiles());
        dirFiles = dirFiles.stream().filter(File::isFile).toList();
        if (!dirFiles.isEmpty()) {
            for (File file : dirFiles) {
                String fileType = file.getName().substring(file.getName().lastIndexOf(".")).substring(1);
                if (map.containsKey(fileType)) {
                    List<File> files = map.get(fileType);
                    files.add(file);
                    map.put(fileType, files);
                } else {
                    List<File> files = new ArrayList<>();
                    files.add(file);
                    map.put(fileType, files);
                }
            }
        }
        return map;
    }

    @Override
    public List<File> getDirectories(DirType dirTypeEnum) {
        List<File> dirFiles = List.of(dirTypeEnum == DirType.INPUT ? getInputDirFiles() : getOutputDirFiles());
        dirFiles = dirFiles.stream().filter(File::isDirectory).toList();

        return dirFiles;
    }



}
