package edu.itstep.solarSystem.converter;

import edu.itstep.solarSystem.dto.SunDto;
import edu.itstep.solarSystem.model.Sun;

public interface SunConverter {

    Sun convert(SunDto source);

    SunDto convert(Sun source);
}
