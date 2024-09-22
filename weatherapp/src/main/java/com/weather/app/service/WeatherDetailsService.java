package com.weather.app.service;

import com.weather.app.dto.WeatherDetailsResponseDTO;

public interface WeatherDetailsService {
    WeatherDetailsResponseDTO getWeatherDetails(String postalCode, String userName);
}
