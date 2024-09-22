package com.weather.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainWeatherDetailsDTO {
    private double temp;
    private int humidity;
    private double temp_min;
    private double temp_max;
}
