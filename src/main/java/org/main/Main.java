package org.main;

import org.config.CProperties;
import org.config.CPropertiesImpl;
import org.config.DirType;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        CProperties properties = new CPropertiesImpl(Main.class);
        properties.loadProperties("config.properties");
        System.out.println(properties.getInputDir());
        System.out.println(properties.getOutputDir());
        System.out.println(properties.filesGroupByFileType(DirType.INPUT));
        System.out.println(properties.getDirectories(DirType.INPUT));
        File input = properties.filesGroupByFileType(DirType.INPUT).get("txt").get(0);
        System.out.println(input);
    }
}