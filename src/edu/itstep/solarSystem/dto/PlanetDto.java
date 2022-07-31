package edu.itstep.solarSystem.dto;

import edu.itstep.solarSystem.dto.parent.TypeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanetDto extends TypeDto {

    private Long sunId;
    private boolean inhabitant;
    private Long population;
    private List<SatelliteDto> satellites;

}
