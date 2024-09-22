package com.weather.app.dto;

import lombok.Data;

@Data
public class WeatherDetailsResponseDTO {
    private String weatherDescription;
    private double temperature;
    private double temperatureMaximum;
    private double temperatureMinimum;
    private int humidity;
}
