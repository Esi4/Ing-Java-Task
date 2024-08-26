package org.ing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadInputFile {
    private String pathToFile;
    private HashMap<String, int[]> hashMap = new HashMap<>();
    private File file;

    public ReadInputFile(String pathToFile) {
        if(!isValidFilePath(pathToFile)) {throw new IllegalArgumentException();}

        this.pathToFile = pathToFile;
    }

    public boolean isValidFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }

        file = new File(filePath);
        return file.exists() && file.isFile() && file.canRead();
    }

    private void readFile() {
        ArrayList<int[]> arr = new ArrayList<>();
        BufferedReader bf;

        try {
            bf = new BufferedReader(new FileReader(pathToFile));
            //TODO()

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
