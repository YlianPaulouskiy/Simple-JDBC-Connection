package edu.itstep.solarSystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.itstep.solarSystem.constants.Constants.DATE_TIME_PATTERN;

public class DateUtils {

    public static String getDate(Date date) {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }
}
