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

    // Основной метод для группировки строк
    private void groupLines() {
        // Множество для хранения необработанных строк
        Set<String[]> unprocessedLines = new HashSet<>(readLines);
        Map<String, Set<String[]>> elementToLinesMap = createElementToLinesMap(unprocessedLines);

        // Пока есть необработанные строки
        while (!unprocessedLines.isEmpty()) {
            // Берём первую необработанную строку и создаём новую группу
            String[] currentLine = unprocessedLines.iterator().next();
            Set<String[]> currentGroup = new HashSet<>();
            Queue<String[]> queue = new LinkedList<>();
            queue.add(currentLine);

            // Поиск в ширину (BFS) для нахождения связанных строк
            while (!queue.isEmpty()) {
                String[] line = queue.poll();
                if (!unprocessedLines.contains(line)) continue;  // Пропускаем, если строка уже обработана
                unprocessedLines.remove(line);
                currentGroup.add(line);

                // Поиск всех строк, имеющих хотя бы одно совпадение
                for (String element : line) {
                    if (element.isEmpty()) continue;  // Пропускаем пустые элементы
                    Set<String[]> relatedLines = elementToLinesMap.getOrDefault(element, Collections.emptySet());
                    for (String[] relatedLine : relatedLines) {
                        if (unprocessedLines.contains(relatedLine)) {
                            queue.add(relatedLine);
                        }
                    }
                }
            }
            if (currentGroup.size() > 1) {
                groups.add(currentGroup);  // Добавляем только группы с более чем одной строкой
            }
        }

        // Сортировка групп по количеству элементов в убывающем порядке
        groups.sort((g1, g2) -> Integer.compare(g2.size(), g1.size()));
    }

    // Метод для создания структуры Map, отображающей значения элементов в строки, содержащие эти значения
    private Map<String, Set<String[]>> createElementToLinesMap(Set<String[]> lines) {
        Map<String, Set<String[]>> elementToLinesMap = new HashMap<>();
        for (String[] line : lines) {
            for (String element : line) {
                if (element.isEmpty()) continue;  // Пропускаем пустые элементы
                elementToLinesMap.computeIfAbsent(element, k -> new HashSet<>()).add(line);
            }
        }
        return elementToLinesMap;
    }
}
