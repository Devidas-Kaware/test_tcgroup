package com.weather.app.controller;

import com.weather.app.dto.WeatherDetailsRequestDTO;
import com.weather.app.dto.WeatherDetailsResponseDTO;
import com.weather.app.service.WeatherDetailsService;
import com.weather.app.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/history")
    public ResponseEntity<?> getWeatherHistory(@RequestParam(required = false) String postalCode,
                                               @RequestParam(required = false) String userName) {
        return weatherDetailsService.getWeatherHistory(postalCode, userName);
    }
}

