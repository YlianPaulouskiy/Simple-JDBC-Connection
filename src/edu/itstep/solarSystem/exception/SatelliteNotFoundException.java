package edu.itstep.solarSystem.exception;

public class SatelliteNotFoundException extends RuntimeException {

    public SatelliteNotFoundException() {
        this("Satellite NOt Found!");
    }

    public SatelliteNotFoundException(String message) {
        super(message);
    }
}
