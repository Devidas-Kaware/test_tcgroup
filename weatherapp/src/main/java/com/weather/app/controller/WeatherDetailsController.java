package com.weather.app.controller;

import com.weather.app.dto.WeatherDetailsRequestDTO;
import com.weather.app.dto.WeatherDetailsDTO;
import com.weather.app.dto.WeatherDetailsResponseDTO;
import com.weather.app.service.WeatherDetailsService;
import com.weather.app.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weatherDetails")
public class WeatherDetailsController {

    @Autowired
    private WeatherDetailsService weatherDetailsService;

    @PostMapping("/fetch")
    public ResponseEntity<?> fetchWeather(@Valid @RequestBody WeatherDetailsRequestDTO weatherDetailsRequestDTO) {
        WeatherDetailsResponseDTO response = weatherDetailsService.getWeatherDetails(weatherDetailsRequestDTO.getPostalCode(),
                weatherDetailsRequestDTO.getUserName());
        return Response.success(response);
    }
}

