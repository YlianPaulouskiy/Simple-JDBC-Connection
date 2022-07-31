package edu.itstep.solarSystem.init;

import edu.itstep.solarSystem.conf.ConfProvider;
import edu.itstep.solarSystem.connection.ConnectionProvider;
import edu.itstep.solarSystem.singleton.ConnectionSingleton;
import edu.itstep.solarSystem.singleton.QuerySingleton;

public class Init {

    public void init() {
        ConnectionSingleton.instance(new ConnectionProvider().connection());
        QuerySingleton.instance(new ConfProvider().queryList());
    }
}
