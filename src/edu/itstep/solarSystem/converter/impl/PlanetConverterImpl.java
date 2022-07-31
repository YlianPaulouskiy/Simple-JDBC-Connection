package edu.itstep.solarSystem.converter.impl;

import edu.itstep.solarSystem.converter.PlanetConverter;
import edu.itstep.solarSystem.dto.PlanetDto;
import edu.itstep.solarSystem.model.Planet;
import edu.itstep.solarSystem.util.DateUtils;

public class PlanetConverterImpl implements PlanetConverter {

    @Override
    public Planet convert(PlanetDto source) {
        if (source == null) {
            return null;
        }
        Planet target = new Planet();
        target.setId(source.getId());
        target.setSunId(source.getSunId());
        target.setName(source.getName());
        target.setType(source.getType());
        target.setInhabitant(source.isInhabitant());
        target.setPopulation(source.getPopulation());
        return target;
    }

    @Override
    public PlanetDto convert(Planet source) {
        if (source == null) {
            return null;
        }
        PlanetDto target = new PlanetDto();
        target.setId(source.getId());
        target.setSunId(source.getSunId());
        target.setName(source.getName());
        target.setType(source.getType());
        target.setInhabitant(source.isInhabitant());
        target.setPopulation(source.getPopulation());
        target.setDateCreation(DateUtils.getDate(source.getDateCreation()));
        target.setLastModified(DateUtils.getDate(source.getLastModified()));
        target.setVersion(source.getVersion());
        return target;
    }
}
