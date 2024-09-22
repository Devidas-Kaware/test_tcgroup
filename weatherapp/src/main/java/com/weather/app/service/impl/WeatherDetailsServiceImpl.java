package com.weather.app.service.impl;

import com.weather.app.dto.WeatherDetailsDTO;
import com.weather.app.dto.WeatherDetailsResponseDTO;
import com.weather.app.entity.WeatherDetails;
import com.weather.app.repository.WeatherDetailsRepository;
import com.weather.app.service.WeatherDetailsService;
import com.weather.app.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class WeatherDetailsServiceImpl implements WeatherDetailsService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherDetailsRepository weatherDetailsRepository;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.country}")
    private String weatherCountry;


    public WeatherDetailsResponseDTO getWeatherDetails(String postalCode, String userName) {
        String url = getUrl(postalCode);

        try {
            ResponseEntity<WeatherDetailsDTO> response = restTemplate.getForEntity(url, WeatherDetailsDTO.class);
            WeatherDetailsDTO weather = response.getBody();

            if (weather != null && weather.getMain() != null) {
                // Save the request details in the database
                WeatherDetails weatherDetails = new WeatherDetails();
                weatherDetails.setUserName(userName);
                weatherDetails.setPostalCode(postalCode);
                weatherDetails.setTemperature(weather.getMain().getTemp());
                weatherDetails.setTemperatureMinimum(weather.getMain().getTemp_min());
                weatherDetails.setTemperatureMaximum(weather.getMain().getTemp_max());
                weatherDetails.setHumidity(weather.getMain().getHumidity());
                weatherDetails.setWeatherDescription(weather.getWeather() != null ? weather.getWeather().get(0).getDescription() : null);

                weatherDetailsRepository.save(weatherDetails);

                return weather.toWeatherDetailsDTO(weatherDetails);
            } else {
                throw new RuntimeException("Error while fetching weather data");
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Error while fetching weather data", e);
        }
    }

    @Override
    public ResponseEntity<?> getWeatherHistory(String postalCode, String userName) {
        List<WeatherDetails> weatherDetailsList = new ArrayList<>();
        if (postalCode != null && userName != null) {
            weatherDetailsList = weatherDetailsRepository.findByPostalCodeAndUserName(postalCode, userName);
        } else if (postalCode != null) {
            weatherDetailsList = weatherDetailsRepository.findByPostalCode(postalCode);
        } else if (userName != null) {
            weatherDetailsList = weatherDetailsRepository.findByUserName(userName);
        } else {
            weatherDetailsList = weatherDetailsRepository.findAll();
        }
        if (weatherDetailsList.isEmpty()) {
            return Response.success("No weather records found!");
        } else {
            return Response.success(weatherDetailsList);
        }
    }

    private String getUrl(String postalCode) {
        return String.format("%s?zip=%s,%s&appid=%s&units=metric", apiUrl, postalCode, weatherCountry, apiKey);
    }
}
