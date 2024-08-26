package org.ing;

import java.io.File;

public class ReadInputFile {
    String pathToFile;

    public ReadInputFile(String pathToFile) {
        if(!isValidFilePath(pathToFile)) {throw new IllegalArgumentException();}

        this.pathToFile = pathToFile;
    }

    public void readInputFile(String pathToFile) {

    }

    public static boolean isValidFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }

        File file = new File(filePath);
        return file.exists() && file.isFile() && file.canRead();
    }
}
