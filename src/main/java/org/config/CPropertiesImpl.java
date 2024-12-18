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
    private String configFilePath = "src/main/resources/config.properties";

    public CPropertiesImpl() {
        super();
        loadProperties(configFilePath);

    }
    public CPropertiesImpl(String configFilePath) {
        super();
        if (configFilePath == null || configFilePath.isEmpty()) {
            throw new IllegalArgumentException( "[Class: "+getClass().getSimpleName()+"] " +"Config file cannot be null or empty");
        }
        this.configFilePath = configFilePath;
        loadProperties(configFilePath);


    }

    public CPropertiesImpl(File inputDir, File outputDir) {
        super();
        if (inputDir == null || outputDir == null) {
            throw new IllegalArgumentException( "[Class: "+getClass().getSimpleName()+"] " +"Input and output directories cannot be null");
        }
        this.inputDir = inputDir;
        this.outputDir = outputDir;
        createDirs();

    }

    @Override
    public void loadProperties(String configFilePath) {
        try {
            super.load(new File(configFilePath).toURI().toURL().openStream());
            try {
                inputDir = new File(super.getProperty("inputDir").endsWith("/") ? super.getProperty("inputDir") : super.getProperty("inputDir") + "/");
            }catch (Exception e){
                throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +"Input directory not found in config file",
                        " try to add inputDir property in config file"
                );
            }
            try {
                outputDir = new File(super.getProperty("outputDir").endsWith("/") ? super.getProperty("outputDir") : super.getProperty("outputDir") + "/");
            }
            catch (Exception e){
                throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +"Output directory not found in config file",
                        " try to add outputDir property in config file"
                );
            }
        } catch (IOException e) {
            throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +" Config file not found: " + configFilePath,
                    " try to create a config file with inputDir and outputDir properties in resources directory"
                    );
        }catch (NullPointerException e){
            throw new InputException("[Class: "+getClass().getSimpleName()+"]"  +" Config file in resources directory not found: " + configFilePath
                    ," try to create a config file with inputDir and outputDir properties in resources directory"
            );
        }
        createDirs();
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
        return outputDir.getAbsolutePath().endsWith("/") ? outputDir.getAbsolutePath() : outputDir.getAbsolutePath() + "/";
    }
    @Override
    public String getOutputDir(String extension) {
        String path = outputDir.getAbsolutePath().endsWith("/") ? outputDir.getAbsolutePath() : outputDir.getAbsolutePath() + "/";
        File file = new File(path+ extension);
        if (!file.exists()){
            boolean isCreated = file.mkdirs();
            if (isCreated){
                System.err.println("[Class: "+getClass().getSimpleName()+"]"  +"Output directory created: " + file.getAbsolutePath());
            }else {
                System.err.println("[Class: "+getClass().getSimpleName()+"]"  +"Output directory creation failed: " + file.getAbsolutePath());
            }
        }
        return file.getAbsolutePath().endsWith("/") ? file.getAbsolutePath() : file.getAbsolutePath() + "/";
    }

    @Override
    public String getInputDir() {
        return inputDir.getAbsolutePath().endsWith("/") ? inputDir.getAbsolutePath() : inputDir.getAbsolutePath() + "/";
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
        try {
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
        }catch (NullPointerException e) {
            throw new InputException("[Class: " + getClass().getSimpleName() + "]" + " No files found in directory: " + dirFiles.get(0).getParentFile().getAbsolutePath(),
                    " try to add files in the directory, change the directory, create directory or check the directory path in config file"
            );
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
