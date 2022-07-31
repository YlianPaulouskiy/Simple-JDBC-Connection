package edu.itstep.solarSystem.model.parent;

import lombok.*;

import java.util.Date;


@Getter
@Setter
public abstract class SpaceBody {

    private String name;
    private Long id;
    private Date dateCreation;
    private Date lastModified;
    private Long version;

}
