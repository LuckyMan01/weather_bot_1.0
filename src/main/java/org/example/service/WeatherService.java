package org.example.service;

import org.example.model.WeatherModel;
import org.example.util.Emoji;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.TimeZone;


public class WeatherService {
    private static final String FILE_NAME = "src/main/resources/application.properties";
    private String id;
    private final WeatherModel weather = new WeatherModel();

    public WeatherService() {
        try (InputStream input = new FileInputStream(FILE_NAME)) {
            Properties prop = new Properties();
            prop.load(input);
            id = prop.getProperty("weather.id");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String buildHttpRequestWeather(String cityName) {
        return String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", cityName, id);
    }

    public String getWeather(BufferedReader bufferedReader) throws IOException {
        String jsonString = bufferedReader.readLine();
        JSONObject obj = new JSONObject(jsonString);
        JSONArray array = obj.getJSONArray("weather");

        for (int i = 0; i < array.length(); i++) {
            weather.setEmoji(array.getJSONObject(i).getString("main"));
        }
        weather.setWind(obj.getJSONObject("wind").getDouble("speed"));
        weather.setFeelLike(obj.getJSONObject("main").getDouble("feels_like"));
        weather.setCityName(obj.getString("name"));
        weather.setTemperature(obj.getJSONObject("main").getDouble("temp"));
        weather.setMaxTemperature(obj.getJSONObject("main").getDouble("temp_max"));
        weather.setMinTemperature(obj.getJSONObject("main").getDouble("temp_min"));
        weather.setSunrise(getDate(obj.getJSONObject("sys").getLong("sunrise")));
        weather.setSunset(getDate(obj.getJSONObject("sys").getLong("sunset")));
        weather.setDurationDay(getDate(obj.getJSONObject("sys").getLong("sunset") - obj.getJSONObject("sys").getLong("sunrise")));

        return createWeatherMessage();
    }

    private String getDate(long longDate) {
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(longDate),
                TimeZone.getDefault().toZoneId());
        return time.toLocalTime().toString();
    }

    private String createWeatherMessage() {
        String emojiWeather = EmojiService.getEmoji(weather.getEmoji());
        return "<b>Місто - </b>" + weather.getCityName() +
                "\n<b>Температура</b> - " + weather.getTemperature() + "°C " + emojiWeather +
                "\n<b>Відчувається</b> - " + weather.getFeelLike().longValue() + "°C " + Emoji.THERMOMETER.label +
                "\n<b>Швидкість вітру</b> - " + weather.getWind() + "m/s " + Emoji.WIND.label +
                "\n<b>Мах температура</b> - " + weather.getMaxTemperature().longValue() + "°C" +
                "\n<b>Мін температура</b> - " + weather.getMinTemperature().longValue() + "°C" +
                "\n<b>Схід сонця</b> - " + weather.getSunrise() + " " + Emoji.SUNRISE.label +
                "\n<b>Тривалість дня</b> - " + weather.getDurationDay() + " " + Emoji.CLOCK.label +
                "\n<b>Захід сонця</b> - " + weather.getSunset() + " " + Emoji.SUNSET.label;
    }
}


