package org.ing;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class ReadInputFile {
    private String pathToFile;
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

    private String[][] readFile() throws IOException {
        ArrayList<String[]> arrayList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(isValidString(line)) {
                    String[] str = line.trim().split(";");
                    arrayList.add(str);
                }
            }
        }

        return arrayList.toArray(new String[arrayList.size()][]);
    }

    public boolean isValidString(String str) {
        String pattern = "^\"(\\d*)\"(;\"(\\d*)\")+$";
        return Pattern.matches(pattern, str);
    }
}
