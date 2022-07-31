package edu.itstep.solarSystem.model;


import edu.itstep.solarSystem.model.parent.SpaceBodyType;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter // если ставить над классом то применяется ко всему классу, но можно ставить только над полями
public class Planet extends SpaceBodyType {

    private Long sunId;
    private boolean inhabitant;
    private long population;

}
