package edu.itstep.solarSystem.service;

import edu.itstep.solarSystem.model.Satellite;

import java.util.List;

public interface SatelliteService {

    Satellite findOne(Long id);

    List<Satellite> findAll();

    List<Satellite> findAllBySunId(Long sunId);

    List<Satellite> findAllByPlanetId(Long planetId);

    Satellite save(Satellite satellite);

    void remove(Long id);
}
