package org.ing;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcessesLines {
    private ReadInputFile readInputFile;

    @Getter
    private List<List<String[]>> groups = new ArrayList<>();

    public ProcessesLines(ReadInputFile readInputFile) {
        this.readInputFile = readInputFile;
    }

    public void processes() {
        Set<Integer> indexLines = new HashSet<>();
        List<String[]> processesLines = readInputFile.getProcessesLines();

        for (int i = 0; i < processesLines.size(); i++) {
            if(indexLines.contains(i)) continue;

            List<String[]> group = new ArrayList<>();
            group.add(processesLines.get(i));
            indexLines.add(i);

            for (int j = i + 1; j < processesLines.size(); j++) {
                if(indexLines.contains(j)) continue;

                if(equalsLines(processesLines.get(i), processesLines.get(j))) {
                    group.add(processesLines.get(j));
                    indexLines.add(j);
                }
            }

            if(group.size() > 1) {groups.add(group);}
        }
    }

    private boolean equalsLines(String[] lineOne, String[] lineTwo) {
        for (int i = 0; i < lineOne.length; i++) {
            if(lineOne[i].equals(lineTwo[i]) && lineOne[i].equals(" ")) {
                return true;
            }
        }

        return false;
    }
}
