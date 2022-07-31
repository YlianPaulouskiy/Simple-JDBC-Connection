package edu.itstep.solarSystem.facade.impl;

import edu.itstep.solarSystem.converter.PlanetConverter;
import edu.itstep.solarSystem.converter.SatelliteConverter;
import edu.itstep.solarSystem.converter.SunConverter;
import edu.itstep.solarSystem.converter.impl.PlanetConverterImpl;
import edu.itstep.solarSystem.converter.impl.SatelliteConverterImpl;
import edu.itstep.solarSystem.converter.impl.SunConverterImpl;
import edu.itstep.solarSystem.dto.PlanetDto;
import edu.itstep.solarSystem.dto.SatelliteDto;
import edu.itstep.solarSystem.dto.SunDto;
import edu.itstep.solarSystem.facade.SolarSystemFacade;

import edu.itstep.solarSystem.model.Planet;
import edu.itstep.solarSystem.model.Satellite;
import edu.itstep.solarSystem.model.Sun;
import edu.itstep.solarSystem.service.PlanetService;
import edu.itstep.solarSystem.service.SatelliteService;
import edu.itstep.solarSystem.service.SunService;
import edu.itstep.solarSystem.service.impl.PlanetServiceImpl;
import edu.itstep.solarSystem.service.impl.SatelliteServiceImpl;
import edu.itstep.solarSystem.service.impl.SunServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SolarSystemFacadeImpl implements SolarSystemFacade {

    // services
    private final SunService sunService;
    private final PlanetService planetService;
    private final SatelliteService satelliteService;
    // converters
    private final SunConverter sunConverter;
    private final SatelliteConverter satelliteConverter;
    private final PlanetConverter planetConverter;

    public SolarSystemFacadeImpl() {
        this.sunService = new SunServiceImpl();
        this.planetService = new PlanetServiceImpl();
        this.satelliteService = new SatelliteServiceImpl();
        this.sunConverter = new SunConverterImpl();
        this.satelliteConverter = new SatelliteConverterImpl();
        this.planetConverter = new PlanetConverterImpl();
    }

    @Override
    public SunDto findOneSun(Long id) {
        SunDto sun = sunConverter.convert(sunService.findOne(id));
        sun.setPlanets(findAllPlanetBySunId(sun.getId()));
        return sun;
    }

    @Override
    public List<SunDto> findAllSuns() {
        List<SunDto> sunList = sunService.findAll()
                .stream().map(sunConverter::convert).collect(Collectors.toList());
        if (sunList.isEmpty()) {
            return Collections.emptyList();
        }
        for (SunDto sun : sunList) {
            sun.setPlanets(findAllPlanetBySunId(sun.getId()));
        }
        return sunList;
    }

    @Override
    public SunDto saveSun(SunDto sunDto) {
        return sunConverter.convert(
                sunService.save(
                        sunConverter.convert(sunDto)
                )
        );
    }

    @Override
    public void removeSun(Long id) {
        sunService.remove(id);
    }

    //PLANETS

    @Override
    public PlanetDto findOnePlanet(Long id) {
        PlanetDto planet = planetConverter.convert(planetService.findOne(id));
        planet.setSatellites(findAllSatelliteByPlanetId(planet.getId()));
        return planet;
    }

    @Override
    public List<PlanetDto> findAllPlanet() {
        return convertedPlanets(planetService.findAll());
    }

    @Override
    public List<PlanetDto> findAllPlanetBySunId(Long sunId) {
        return convertedPlanets(planetService.findAllBySun(sunId));
    }

    @Override
    public PlanetDto savePlanet(PlanetDto planetDto) {
        return planetConverter.convert(
                planetService.save(
                        planetConverter.convert(planetDto)
                )
        );
    }

    @Override
    public void removePlanet(Long id) {
        planetService.remove(id);
    }

    //SATELLITES

    @Override
    public SatelliteDto findOneSatellite(Long id) {
        Satellite satellite = satelliteService.findOne(id);
        return satelliteConverter.convert(satellite);
    }

    @Override
    public List<SatelliteDto> findAllSatellite() {
        List<Satellite> satelliteList = satelliteService.findAll();
        return satelliteList.isEmpty() ?
                Collections.emptyList() :
                satelliteList.stream().map(satelliteConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<SatelliteDto> findAllSatelliteBySunId(Long sunId) {
        List<Satellite> satelliteList = satelliteService.findAllBySunId(sunId);
        return satelliteList.isEmpty() ?
                Collections.emptyList() :
                satelliteList.stream().map(satelliteConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<SatelliteDto> findAllSatelliteByPlanetId(Long planetId) {
        List<Satellite> satelliteList = satelliteService.findAllByPlanetId(planetId);
        return satelliteList.isEmpty() ?
                Collections.emptyList() :
                satelliteList.stream().map(satelliteConverter::convert).collect(Collectors.toList());
    }

    @Override
    public SatelliteDto saveSatellite(SatelliteDto satelliteDto) {
        return satelliteConverter.convert(
                satelliteService.save(
                        satelliteConverter.convert(satelliteDto)
                )
        );
    }

    @Override
    public void removeSatellite(Long id) {
        satelliteService.remove(id);
    }

    //------------------------------------------------------------------------------------------------------------------

    private List<PlanetDto> convertedPlanets(List<Planet> planetList) {
        if (planetList.isEmpty()) {
            return Collections.emptyList();
        }
        List<PlanetDto> planetDtoList = planetList.stream()
                .map(planetConverter::convert).collect(Collectors.toList());
        for (PlanetDto planetDto : planetDtoList) {
            planetDto.setSatellites(findAllSatelliteByPlanetId(planetDto.getId()));
        }
        return planetDtoList;
    }

}
