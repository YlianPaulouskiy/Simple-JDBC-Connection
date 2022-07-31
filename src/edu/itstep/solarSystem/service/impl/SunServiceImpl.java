package edu.itstep.solarSystem.service.impl;

import edu.itstep.solarSystem.dao.SunDao;
import edu.itstep.solarSystem.dao.impl.SunDaoImpl;
import edu.itstep.solarSystem.exception.SunException;
import edu.itstep.solarSystem.exception.SunNotFoundException;
import edu.itstep.solarSystem.model.Sun;
import edu.itstep.solarSystem.service.SunService;

import java.util.List;

public class SunServiceImpl implements SunService {

    private final SunDao sunDao;

    public SunServiceImpl() {
        this.sunDao = new SunDaoImpl();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Sun findOne(Long id) {
        return sunDao.findOne(id).orElseThrow(SunNotFoundException::new);
    }

    @Override
    public List<Sun> findAll() {
        return sunDao.findAll();
    }

    @Override
    public Sun save(Sun sun) {
        return sun.getId() == null ?
                sunDao.create(sun).orElseThrow(() -> new SunException("Sun not created")) :
                sunDao.update(sun).orElseThrow(() -> new SunException("Sun not updated"));
    }

    @Override
    public void remove(Long id) {
        sunDao.remove(id);
    }
}
