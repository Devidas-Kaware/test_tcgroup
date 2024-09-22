package com.weather.app.repository;

import com.weather.app.entity.WeatherDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDetailsRepository extends JpaRepository<WeatherDetails, Long> {
}
