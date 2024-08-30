package org.ing;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.*;

public class ProcessesLines {
    private ExecutorService executorService;
    private final List<String[]> readLines;
    @Getter
    private List<Set<String[]>> groups;


    public ProcessesLines(List<String[]> readLines) {
        this.readLines = readLines;
        this.groups = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        groupLines();
        this.executorService.shutdown();
    }

    private void groupLines() {
        Set<String[]> unprocessedLines = new HashSet<>(readLines);
        Map<String, Set<String[]>> elementToLinesMap = createElementToLinesMap(unprocessedLines);
        List<Future<Set<String[]>>> futures = new ArrayList<>();

        while (!unprocessedLines.isEmpty()) {
            String[] currentLine = unprocessedLines.iterator().next();
            unprocessedLines.remove(currentLine);

            GroupProcessorTask task = new GroupProcessorTask(currentLine, elementToLinesMap, unprocessedLines);
            futures.add(executorService.submit(task));
        }

        for (Future<Set<String[]>> future : futures) {
            try {
                Set<String[]> group = future.get();
                if (group.size() > 1) {
                    groups.add(group);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        groups.sort((g1, g2) -> Integer.compare(g2.size(), g1.size()));
    }

    private Map<String, Set<String[]>> createElementToLinesMap(Set<String[]> lines) {
        Map<String, Set<String[]>> elementToLinesMap = new HashMap<>();
        for (String[] line : lines) {
            for (String element : line) {
                if (element.equals("\"\"")) continue;
                elementToLinesMap.computeIfAbsent(element, k -> new HashSet<>()).add(line);
            }
        }
        return elementToLinesMap;
    }
}
