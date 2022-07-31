package edu.itstep.solarSystem.converter;

import edu.itstep.solarSystem.dto.SatelliteDto;
import edu.itstep.solarSystem.model.Satellite;

public interface SatelliteConverter {

    Satellite convert(SatelliteDto source);

    SatelliteDto convert(Satellite source);
}
