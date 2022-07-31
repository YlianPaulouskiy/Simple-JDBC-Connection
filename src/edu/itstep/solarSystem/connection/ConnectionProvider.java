package edu.itstep.solarSystem.connection;

import edu.itstep.solarSystem.conf.ConfProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionProvider {

    private final ConfProvider confProvider;

    public ConnectionProvider() {
        this.confProvider = new ConfProvider();
    }

    public Connection connection() {
        Map<String, String> map = confProvider.connectionConf();
        if (!map.isEmpty()) {
            try {
                Class.forName(map.get("driver"));
                return DriverManager.getConnection(
                        map.get("url"),
                        map.get("username"),
                        map.get("password")
                );
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
