package org.example.model;

import java.io.File;

public class CheckFile {
    public static CheckFile instance;

    public CheckFile() {
    }

    public static CheckFile getInstance() {
        if (instance == null) {
            instance = new CheckFile();
        }
        return instance;
    }

    public boolean checkFileExisted(String path) {
        return new File(path).exists();
    }

    public boolean checkDirectoryExisted(String path) {
        if(!path.contains(File.separator)){
            return false;
        }
        String dir = path.substring(0, path.lastIndexOf(File.separator));
        return new File(dir).isDirectory();
    }

}
