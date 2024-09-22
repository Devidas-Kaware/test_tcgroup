package com.weather.app.dto;

import com.weather.app.entity.WeatherDetails;
import lombok.Data;

import java.util.List;

@Data
public class WeatherDetailsDTO {
    private List<WeatherDescription> weather;
    private MainWeatherDetailsDTO main;

    public WeatherDetailsResponseDTO toWeatherDetailsDTO(WeatherDetails weather) {
        WeatherDetailsResponseDTO weatherDetailsResponseDTO = new WeatherDetailsResponseDTO();
        weatherDetailsResponseDTO.setWeatherDescription(weather.getWeatherDescription());
        weatherDetailsResponseDTO.setTemperature(weather.getTemperature());
        weatherDetailsResponseDTO.setTemperatureMaximum(weather.getTemperatureMaximum());
        weatherDetailsResponseDTO.setTemperatureMinimum(weather.getTemperatureMinimum());
        weatherDetailsResponseDTO.setHumidity(weather.getHumidity());
        return weatherDetailsResponseDTO;
    }
}
