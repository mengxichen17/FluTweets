package edu.upenn.cit594.util;

import java.util.List;

public class Tweet {
    protected double lat;
    protected double lon;
    protected String content;

    public Tweet(double lat, double lon, String content) {
        this.lat = lat;
        this.lon = lon;
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    /**
     * Find the state of this tweet, the lowest cartesian distance from this tweet
     * @param states the list of states' latitudes and longitudes
     * @return the nearest state
     */
    public State findState(List<State> states) {
        double nearest = Double.POSITIVE_INFINITY;
        State result = null;
        for (State state : states) {
            double dist = Math.sqrt(Math.pow((this.lat - state.lat), 2) + Math.pow((this.lon - state.lon), 2));
            if (dist < nearest) {
                nearest = dist;
                result = state;
            }
        }
        return result;
    }
}
