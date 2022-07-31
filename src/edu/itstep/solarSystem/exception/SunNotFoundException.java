package edu.itstep.solarSystem.exception;

public class SunNotFoundException extends RuntimeException {

    public SunNotFoundException() {
        this("Sun not found");
    }

    public SunNotFoundException(String message) {
        super(message);
    }
}
