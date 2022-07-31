package edu.itstep.solarSystem.service;

import edu.itstep.solarSystem.model.Sun;

import java.util.List;

public interface SunService {

    Sun findOne(Long id);

    List<Sun> findAll();

    Sun save(Sun sun);

    void remove(Long id);

}
