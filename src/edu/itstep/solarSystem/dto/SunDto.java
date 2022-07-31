package edu.itstep.solarSystem.dto;

import edu.itstep.solarSystem.dto.parent.TypeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SunDto extends TypeDto {

    private List<PlanetDto> planets;

}
