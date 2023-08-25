package org.example.util;

public enum Emoji {
    WIND(new String(Character.toChars(0x1F390))),
    FIRE(new String(Character.toChars(0x1F525))),
    CLOCK(new String(Character.toChars(0x1F550))),
    SUNSET(new String(Character.toChars(0x1F306))),
    THUNDERSTORM("\u26C8"),
    SNOW("\u2744"),
    SUNNY("\u2600"),
    CLOUDY("\u2601"),
    SUNRISE("&#127749"),
    THERMOMETER("&#127777");

    public final String label;

    Emoji(String label) {
        this.label = label;
    }
}
