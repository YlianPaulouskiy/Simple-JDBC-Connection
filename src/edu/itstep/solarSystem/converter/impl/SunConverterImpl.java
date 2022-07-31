package edu.itstep.solarSystem.converter.impl;

import edu.itstep.solarSystem.converter.SunConverter;
import edu.itstep.solarSystem.dto.SunDto;
import edu.itstep.solarSystem.model.Sun;
import edu.itstep.solarSystem.util.DateUtils;

public class SunConverterImpl implements SunConverter {


    @Override
    //для пользоввателя, потому что ему не нужно видеть эти 3 поля
    public Sun convert(SunDto source) {
        if (source == null) {
            return null;
        }
        Sun target = new Sun();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setType(source.getType());
        return target;
    }

    @Override
    //для администратора
    public SunDto convert(Sun source) {
        if (source == null) {
            return null;
        }
        SunDto target = new SunDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setType(source.getType());
        target.setDateCreation(DateUtils.getDate(source.getDateCreation()));
        target.setLastModified(DateUtils.getDate(source.getLastModified()));
        target.setVersion(source.getVersion());
        return target;
    }
}
