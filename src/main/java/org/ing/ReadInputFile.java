package org.ing;

import lombok.Getter;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class ReadInputFile {
    private String pathToFile;
    private File file;

    @Getter
    private List<String[]> processesLines = new ArrayList<>();

    public ReadInputFile(String pathToFile) {
        if(!isValidFilePath(pathToFile)) {throw new IllegalArgumentException();}

        this.pathToFile = pathToFile;
        try {
            readFile();
        } catch (IOException e) {
            System.err.println("Error read file" + e.getMessage());
        }
    }

    public boolean isValidFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }

        file = new File(filePath);
        return file.exists() && file.isFile() && file.canRead();
    }

    private void readFile() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(isValidString(line)) {
                    String[] str = line.trim().split(";");
                    processesLines.add(str);
                }
            }
        }
    }

    public boolean isValidString(String str) {
        String pattern = "^\"(\\d*)\"(;\"(\\d*)\")+$";
        return Pattern.matches(pattern, str);
    }
}
