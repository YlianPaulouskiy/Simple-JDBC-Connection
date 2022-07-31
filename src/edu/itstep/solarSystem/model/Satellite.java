package edu.itstep.solarSystem.model;

import edu.itstep.solarSystem.model.parent.SpaceBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Satellite extends SpaceBody {

    private Long planetId;
}
