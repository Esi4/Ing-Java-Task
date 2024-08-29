package org.ing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WriteInOutputFile {
    private final String pathToOutputFile;
    private List<Set<String[]>> groups;

    public WriteInOutputFile(String pathToFile, List<Set<String[]>> groups) {
        if(!isValidFilePath(pathToFile) || groups.isEmpty()) {throw new IllegalArgumentException();}

        this.pathToOutputFile = pathToFile;
        this.groups = groups;
        try {
            writeInFile();
        } catch (IOException e) {
            System.err.println("Error read file" + e.getMessage());
        }
    }

    public boolean isValidFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }
        File file = new File(filePath);
        return file.exists() && file.isFile() && file.canWrite();
    }

    private void writeInFile()  throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathToOutputFile))) {
            System.out.println("Количество групп с более чем одним элементом: " + groups.size());
            int groupIndex = 1;
            for (Set<String[]> group : groups) {
                bw.write("Группа " + groupIndex++ + "\n");
                for (String[] line : group) {
                    bw.write(Arrays.toString(line) + "\n");
                }
                bw.write("------------------------------------------------------------------------------------ \n");
            }
        }
    }
}
