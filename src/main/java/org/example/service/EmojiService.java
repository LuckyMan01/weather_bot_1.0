package org.example.service;

import org.example.util.Emoji;
import org.example.util.Weather;

import java.util.HashMap;
import java.util.Map;

public class EmojiService {
    private static final Map<String, String> emoji = new HashMap<>();

    static {
        emoji.put(Weather.CLOUDS.label, Emoji.CLOUDY.label);
        emoji.put(Weather.CLEAR.label, Emoji.SUNNY.label);
        emoji.put(Weather.RAIN.label, Emoji.THUNDERSTORM.label);
        emoji.put(Weather.THUNDERSTORM.label, Emoji.THUNDERSTORM.label);
        emoji.put(Weather.DRIZZLE.label, Emoji.CLOUDY.label);
        emoji.put(Weather.SNOW.label, Emoji.SNOW.label);
    }

    public static String getEmoji(String emojiValue) {
        if (emoji.containsKey(emojiValue)) {
            return emoji.get(emojiValue);
        } else {
            return Emoji.FIRE.label;
        }
    }

}
