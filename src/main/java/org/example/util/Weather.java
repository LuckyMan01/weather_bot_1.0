package org.example.util;

public enum Weather {
    THUNDERSTORM("Thunderstorm"),
    DRIZZLE("Drizzle"),
    RAIN("Rain"),
    SNOW("Snow"),
    CLEAR("Clear"),
    CLOUDS("Clouds");

    public final String label;

    private Weather(String label) {
        this.label = label;
    }
}
