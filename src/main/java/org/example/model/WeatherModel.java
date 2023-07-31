package org.example.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class WeatherModel {
    private String cityName;
    private Double feelLike;
    private Double temperature;
    private Double maxTemperature;
    private Double minTemperature;
    private String sunrise;
    private String sunset;
    private String emoji;
    private Double wind;
    private String durationDay;

}
