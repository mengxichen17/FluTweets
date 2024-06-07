package edu.upenn.cit594.util;

public class State {
    protected double lat;
    protected double lon;
    protected String name;

    public State(String name, double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;


    }

    public String getName() {
        return this.name;
    }

}
