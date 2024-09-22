package com.weather.app.repository;

import com.weather.app.entity.WeatherDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherDetailsRepository extends JpaRepository<WeatherDetails, Long> {
    List<WeatherDetails> findByPostalCodeAndUserName(String postalCode, String userName);

    List<WeatherDetails> findByPostalCode(String postalCode);

    List<WeatherDetails> findByUserName(String userName);
}
