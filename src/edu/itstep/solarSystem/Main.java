package edu.itstep.solarSystem;

import edu.itstep.solarSystem.dto.PlanetDto;
import edu.itstep.solarSystem.dto.SatelliteDto;
import edu.itstep.solarSystem.dto.SunDto;
import edu.itstep.solarSystem.facade.SolarSystemFacade;
import edu.itstep.solarSystem.facade.impl.SolarSystemFacadeImpl;
import edu.itstep.solarSystem.init.Init;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        new Init().init();

        //точка доступа ко всем данным
        SolarSystemFacade facade = new SolarSystemFacadeImpl();
        List<SunDto> sunDtoList = facade.findAllSuns();
        for (SunDto sunDto : sunDtoList) {
            System.out.println(sunDto.getId() + " " + sunDto.getName());
        }

        System.out.println(sunDtoList.get(0).getPlanets().get(2).getName());

        System.out.println("---------------");



        List<PlanetDto> planetDtoList = facade.findAllPlanet();
        planetDtoList.forEach(planet -> System.out.println(planet.getId() + " " + planet.getName()));

        System.out.println("---------------");

        SatelliteDto satellite = facade.findOneSatellite(1L);
        System.out.println(satellite.getName());


    }
}
