package edu.itstep.solarSystem.facade;

import edu.itstep.solarSystem.dto.PlanetDto;
import edu.itstep.solarSystem.dto.SatelliteDto;
import edu.itstep.solarSystem.dto.SunDto;

import java.util.List;

public interface SolarSystemFacade {

    SunDto findOneSun(Long id);

    List<SunDto> findAllSuns();

    SunDto saveSun(SunDto sunDto);

    void removeSun(Long id);

    //planet

    PlanetDto findOnePlanet(Long id);

    List<PlanetDto> findAllPlanet();

    List<PlanetDto> findAllPlanetBySunId(Long sunId);

    PlanetDto savePlanet(PlanetDto planetDto);

    void removePlanet(Long id);

    //satellite

    SatelliteDto findOneSatellite(Long id);

    List<SatelliteDto> findAllSatellite();

    List<SatelliteDto> findAllSatelliteBySunId(Long sunId);

    List<SatelliteDto> findAllSatelliteByPlanetId(Long planetId);

    SatelliteDto saveSatellite(SatelliteDto satelliteDto);

    void removeSatellite(Long id);

}
