package edu.itstep.solarSystem.converter.impl;

import edu.itstep.solarSystem.converter.SatelliteConverter;
import edu.itstep.solarSystem.dto.SatelliteDto;
import edu.itstep.solarSystem.model.Satellite;
import edu.itstep.solarSystem.util.DateUtils;

public class SatelliteConverterImpl implements SatelliteConverter {

    @Override
    public Satellite convert(SatelliteDto source) {
        if (source == null) {
            return null;
        }
        Satellite target = new Satellite();
        target.setId(source.getId());
        target.setPlanetId(source.getPlanetId());
        target.setName(source.getName());
        return target;
    }

    @Override
    public SatelliteDto convert(Satellite source) {
        if (source == null) {
            return null;
        }
        SatelliteDto target = new SatelliteDto();
        target.setId(source.getId());
        target.setPlanetId(source.getPlanetId());
        target.setName(source.getName());
        target.setDateCreation(DateUtils.getDate(source.getDateCreation()));
        target.setLastModified(DateUtils.getDate(source.getLastModified()));
        target.setVersion(source.getVersion());
        return target;
    }
}
