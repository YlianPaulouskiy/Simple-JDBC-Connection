package edu.itstep.solarSystem.dao;

import edu.itstep.solarSystem.model.Planet;

import java.util.List;

public interface PlanetDao extends  JdbcRepository<Planet>{

    List<Planet> findAllBySunId(Long sunId);

}
