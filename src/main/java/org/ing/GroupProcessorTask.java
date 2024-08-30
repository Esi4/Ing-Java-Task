package org.ing;

import java.util.*;
import java.util.concurrent.Callable;

public class GroupProcessorTask implements Callable<Set<String[]>> {
    private final String[] currentLine;
    private final Map<String, Set<String[]>> elementToLinesMap;
    private final Set<String[]> unprocessedLines;

    public GroupProcessorTask(String[] currentLine, Map<String, Set<String[]>> elementToLinesMap, Set<String[]> unprocessedLines) {
        this.currentLine = currentLine;
        this.elementToLinesMap = elementToLinesMap;
        this.unprocessedLines = unprocessedLines;
    }

    @Override
    public Set<String[]> call() {
        Set<String[]> currentGroup = new HashSet<>();
        Queue<String[]> queue = new LinkedList<>();
        queue.add(currentLine);

        while (!queue.isEmpty()) {
            String[] line = queue.poll();
            if (!unprocessedLines.contains(line)) continue;
            synchronized (unprocessedLines) {
                unprocessedLines.remove(line);
            }
            currentGroup.add(line);

            for (String element : line) {
                if (element.equals("\"\"")) continue;
                Set<String[]> relatedLines = elementToLinesMap.getOrDefault(element, Collections.emptySet());
                for (String[] relatedLine : relatedLines) {
                    synchronized (unprocessedLines) {
                        if (unprocessedLines.contains(relatedLine)) {
                            queue.add(relatedLine);
                        }
                    }
                }
            }
        }
        return currentGroup;
    }
}
