package edu.itstep.solarSystem.converter;

import edu.itstep.solarSystem.dto.PlanetDto;
import edu.itstep.solarSystem.model.Planet;

public interface PlanetConverter {

    Planet convert(PlanetDto source);

    PlanetDto convert(Planet source);
}
