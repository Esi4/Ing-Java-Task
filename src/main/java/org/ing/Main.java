package org.ing;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static String pathToFile = "src/main/resources/input.txt";
    public static String pathToOutputFile = "src/main/resources/output";

    public static void main(String[] args) {
        ReadInputFile readInputFile = new ReadInputFile(pathToFile);
        ProcessesLines processesLines = new ProcessesLines(readInputFile.getReadLines());
        //ProcessesLines processesLines = new ProcessesLines(exampleLines);
        WriteInOutputFile writeInOutputFile = new WriteInOutputFile(pathToOutputFile, processesLines.getGroups());

    }
}