package edu.itstep.solarSystem.conf;

import edu.itstep.solarSystem.constants.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfProvider {

    private final ConfReader confReader;

    public ConfProvider() {
        this.confReader = new ConfReader();
    }

    public Map<String,String> connectionConf() {
        return getMap(Constants.CONNECTION_CONF);
    }

    public Map<String,String> queryList() {
        return getMap(Constants.QUERY_LIST);
    }

    private Map<String,String> getMap(String filePathName) {
        List<String> allLines = confReader.getAllLines(filePathName);
        Map<String, String> map = new HashMap<>(allLines.size());

        for (String line : allLines) {
            String[] array = getArray(line);
            if (array.length == 2) {
                map.put(array[0], array[1]);
            }
        }
        return map;
    }

    private String[] getArray(String line) {
        String[] array = line.split("=");
        if (array.length < 2) {
            return new String[0];
        } else if (array.length == 2) {
            return array;
        } else {
            StringBuilder value = new StringBuilder();
            for (int i = 1; i < array.length; i++) {
                value.append(array[i]).append("=");
            }
            value.setLength(value.length() - 1);
            return new String[] {array[0], value.toString()};
        }

    }
}
