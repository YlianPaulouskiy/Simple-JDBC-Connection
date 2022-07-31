package edu.itstep.solarSystem.service;

import edu.itstep.solarSystem.model.Planet;

import java.util.List;

public interface PlanetService {

    Planet findOne(Long id);

    List<Planet> findAll();

    List<Planet> findAllBySun(Long sunId);

    Planet save(Planet planet);

    void remove(Long id);

}
