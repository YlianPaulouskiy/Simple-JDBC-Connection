package edu.itstep.solarSystem.dto;

import edu.itstep.solarSystem.dto.parent.TypeDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatelliteDto extends TypeDto {

    private long planetId;

}
