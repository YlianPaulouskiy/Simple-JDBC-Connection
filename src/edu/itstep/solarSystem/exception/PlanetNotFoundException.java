package edu.itstep.solarSystem.exception;

public class PlanetNotFoundException extends RuntimeException {

    public PlanetNotFoundException() {
        this("Planet not found");
    }

    public PlanetNotFoundException(String message) {
        super(message);
    }
}
