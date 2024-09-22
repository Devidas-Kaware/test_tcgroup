package com.weather.app.service;

import com.weather.app.dto.WeatherDetailsResponseDTO;
import org.springframework.http.ResponseEntity;

public interface WeatherDetailsService {
    WeatherDetailsResponseDTO getWeatherDetails(String postalCode, String userName);

    ResponseEntity<?> getWeatherHistory(String postalCode, String userName);
}
