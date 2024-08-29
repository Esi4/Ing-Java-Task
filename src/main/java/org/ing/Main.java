package org.ing;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static String pathToFile = "src/main/resources/input";
    public static String pathToOutputFile = "src/main/resources/output";

    public static void main(String[] args) {
        List<String[]> exampleLines = Arrays.asList(
                new String[]{"111", "123", "222"},
                new String[]{"200", "123", "100"},
                new String[]{"300", "", "100"},
                new String[]{"100", "200", "300"},
                new String[]{"200", "300", "100"}
        );

        ReadInputFile readInputFile = new ReadInputFile(pathToFile);
        //ProcessesLines processesLines = new ProcessesLines(readInputFile.getReadLines());
        ProcessesLines processesLines = new ProcessesLines(exampleLines);
        WriteInOutputFile writeInOutputFile = new WriteInOutputFile(pathToOutputFile, processesLines.getGroups());

    }
}