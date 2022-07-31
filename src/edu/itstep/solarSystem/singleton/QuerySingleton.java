package edu.itstep.solarSystem.singleton;

import java.util.HashMap;
import java.util.Map;

public class QuerySingleton {

    private static QuerySingleton instance;
    private final Map<String, String> queryMap;

    private QuerySingleton(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public static QuerySingleton instance(Map<String, String> queryMap) {
        if (instance == null) {
            instance = new QuerySingleton(new HashMap<>());
        }
        if (queryMap != null) {
            instance = new QuerySingleton(queryMap);
        }
        return instance;
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }

    public String getQuery(String key) {
        return queryMap.getOrDefault(key, "");
    }
}
