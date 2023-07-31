package org.example.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.Emoji;
import org.example.util.Weather;

@Data
@NoArgsConstructor(force = true)
public class EmojiService {

    public static String getEmoji(String emojiValue) {
        if (Weather.CLEAR.label.equals(emojiValue)) {
            emojiValue = Emoji.SUNNY.label;
        } else if (Weather.RAIN.label.equals(emojiValue) || Weather.THUNDERSTORM.label.equals(emojiValue)) {
            emojiValue = Emoji.THUNDERSTORM.label;
        } else if (Weather.CLOUDS.label.equals(emojiValue) || Weather.DRIZZLE.label.equals(emojiValue)) {
            emojiValue = Emoji.CLOUDY.label;
        } else if (Weather.SNOW.label.equals(emojiValue)) {
            emojiValue = Emoji.SNOW.label;
        } else {
            emojiValue = Emoji.FIRE.label;
        }
        return emojiValue;
    }

}
