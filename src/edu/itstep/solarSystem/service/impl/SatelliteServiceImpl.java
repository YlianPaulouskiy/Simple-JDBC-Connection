package edu.itstep.solarSystem.service.impl;

import edu.itstep.solarSystem.dao.SatelliteDao;
import edu.itstep.solarSystem.dao.impl.SatelliteDaoImpl;
import edu.itstep.solarSystem.exception.SatelliteException;
import edu.itstep.solarSystem.exception.SatelliteNotFoundException;
import edu.itstep.solarSystem.model.Satellite;
import edu.itstep.solarSystem.service.SatelliteService;

import java.util.List;

public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteDao satelliteDao;

    public SatelliteServiceImpl() {
        satelliteDao = new SatelliteDaoImpl();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Satellite findOne(Long id) {
        return satelliteDao.findOne(id).orElseThrow(SatelliteNotFoundException::new);
    }

    @Override
    public List<Satellite> findAll() {
        return satelliteDao.findAll();
    }

    @Override
    public List<Satellite> findAllBySunId(Long sunId) {
        return satelliteDao.findAllBySunId(sunId);
    }

    @Override
    public List<Satellite> findAllByPlanetId(Long planetId) {
        return satelliteDao.findAllByPlanetId(planetId);
    }

    @Override
    public Satellite save(Satellite satellite) {
        return satellite.getId() == null ?
                satelliteDao.create(satellite).orElseThrow(() -> new SatelliteException("Satellite not created")) :
                satelliteDao.update(satellite).orElseThrow(() -> new SatelliteException("Satellite not updated"));
    }

    @Override
    public void remove(Long id) {
        satelliteDao.remove(id);
    }
}
