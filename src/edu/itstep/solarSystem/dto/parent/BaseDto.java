package edu.itstep.solarSystem.dto.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto {

    private String name;
    private Long id;
    private String dateCreation;
    private String lastModified;
    private Long version;
}
