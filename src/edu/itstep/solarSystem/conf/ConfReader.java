package edu.itstep.solarSystem.conf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class ConfReader {

    List<String> getAllLines(String filePathName) {
        List<String> allLines = readConf(filePathName);
        allLines.removeIf(line -> line.trim().startsWith("#"));
        return allLines;
    }

    private List<String> readConf(String filePathName){
        try {
            return Files.readAllLines(Paths.get(filePathName), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }
}
