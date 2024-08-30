package org.ing;

import lombok.Getter;

import java.util.*;

public class ProcessesLines {
    private final List<String[]> readLines;

    @Getter
    private List<Set<String[]>> groups;

    public ProcessesLines(List<String[]> readLines) {
        this.readLines = readLines;
        this.groups = new ArrayList<>();
        groupLines();
    }

    private void groupLines() {
        Set<String[]> unprocessedLines = new HashSet<>(readLines);
        Map<String, Set<String[]>> elementToLinesMap = createElementToLinesMap(unprocessedLines);

        while (!unprocessedLines.isEmpty()) {
            String[] currentLine = unprocessedLines.iterator().next();
            Set<String[]> currentGroup = new HashSet<>();
            Queue<String[]> queue = new LinkedList<>();
            queue.add(currentLine);

            while (!queue.isEmpty()) {
                String[] line = queue.poll();
                if (!unprocessedLines.contains(line)) continue;
                unprocessedLines.remove(line);
                currentGroup.add(line);


                for (String element : line) {
                    if (element.equals("\"\"")) continue;
                    Set<String[]> relatedLines = elementToLinesMap.getOrDefault(element, Collections.emptySet());
                    for (String[] relatedLine : relatedLines) {
                        if (unprocessedLines.contains(relatedLine)) {
                            queue.add(relatedLine);
                        }
                    }
                }
            }
            if (currentGroup.size() > 1) {
                groups.add(currentGroup);
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
