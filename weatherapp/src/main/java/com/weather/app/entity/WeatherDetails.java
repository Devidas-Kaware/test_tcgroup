package com.weather.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_details")
@Data
public class WeatherDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String postalCode;
    private Double temperature;
    private Double temperatureMinimum;
    private Double temperatureMaximum;
    private Integer humidity;
    private String weatherDescription;
    private LocalDateTime timestamp = LocalDateTime.now();
}
