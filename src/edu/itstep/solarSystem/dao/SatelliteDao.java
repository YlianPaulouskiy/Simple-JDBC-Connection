package edu.itstep.solarSystem.dao;

import edu.itstep.solarSystem.model.Satellite;

import java.util.List;

public interface SatelliteDao extends JdbcRepository<Satellite> {

    List<Satellite> findAllBySunId(Long sunId);

    List<Satellite> findAllByPlanetId(Long planetId);

}
