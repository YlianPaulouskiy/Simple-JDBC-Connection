package edu.itstep.solarSystem.service.impl;

import edu.itstep.solarSystem.dao.PlanetDao;
import edu.itstep.solarSystem.dao.impl.PlanetDaoImpl;
import edu.itstep.solarSystem.exception.PlanetException;
import edu.itstep.solarSystem.exception.PlanetNotFoundException;
import edu.itstep.solarSystem.model.Planet;
import edu.itstep.solarSystem.service.PlanetService;

import java.util.List;

public class PlanetServiceImpl implements PlanetService {

    private final PlanetDao planetDao;

    public PlanetServiceImpl() {
        this.planetDao = new PlanetDaoImpl();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Planet findOne(Long id) {
        return planetDao.findOne(id).orElseThrow(PlanetNotFoundException::new);
    }

    @Override
    public List<Planet> findAll() {
        return planetDao.findAll();
    }

    @Override
    public List<Planet> findAllBySun(Long sunId) {
        return planetDao.findAllBySunId(sunId);
    }

    @Override
    public Planet save(Planet planet) {
        return planet.getId() == null ?
                planetDao.create(planet).orElseThrow(() -> new PlanetException("Planet not created")) :
                planetDao.update(planet).orElseThrow(() -> new PlanetException("Planet not updated"));
    }

    @Override
    public void remove(Long id) {
        planetDao.remove(id);
    }
}
