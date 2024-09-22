package com.weather.app.service.impl;

import com.weather.app.dto.MainWeatherDetailsDTO;
import com.weather.app.dto.WeatherDescription;
import com.weather.app.dto.WeatherDetailsDTO;
import com.weather.app.dto.WeatherDetailsResponseDTO;
import com.weather.app.entity.WeatherDetails;
import com.weather.app.repository.WeatherDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class WeatherDetailsServiceImplTest {

    @InjectMocks
    private WeatherDetailsServiceImpl weatherDetailsService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherDetailsRepository weatherDetailsRepository;

    @Value("${weather.api.url}")
    private String apiUrl = "http://api.openweathermap.org/data/2.5/weather";

    @Value("${weather.api.key}")
    private String apiKey = "ac718d1c0ec5e7f60fb56e7e2d0f8aa8";

    @Value("${weather.api.country}")
    private String weatherCountry = "us";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherDetails_Success() {
        // Mock the weather API response
        WeatherDetailsDTO mockWeatherDetails = new WeatherDetailsDTO();
        mockWeatherDetails.setMain(new MainWeatherDetailsDTO(20.0, 60, 15.0, 25.0));
        mockWeatherDetails.setWeather(Arrays.asList(new WeatherDescription("clear")));

        when(restTemplate.getForEntity(anyString(), eq(WeatherDetailsDTO.class)))
                .thenReturn(ResponseEntity.ok(mockWeatherDetails));

        WeatherDetailsResponseDTO response = weatherDetailsService.getWeatherDetails("90210", "Shyam");

        assertNotNull(response);
        assertEquals(20.0, response.getTemperature());
        verify(weatherDetailsRepository, times(1)).save(any(WeatherDetails.class));
    }

    @Test
    void testGetWeatherDetails_Error() {
        when(restTemplate.getForEntity(anyString(), eq(WeatherDetailsDTO.class)))
                .thenThrow(new RuntimeException("Error fetching data"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            weatherDetailsService.getWeatherDetails("90210", "");
        });

        assertEquals("Error fetching data", exception.getMessage());
    }

    @Test
    void testGetWeatherHistory_NoRecordsFound() {
        when(weatherDetailsRepository.findByPostalCodeAndUserName("90210", "Shyam"))
                .thenReturn(Arrays.asList());

        ResponseEntity<?> response = weatherDetailsService.getWeatherHistory("90210", "Shyam");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetWeatherHistory_RecordsFound() {
        WeatherDetails weatherDetail = new WeatherDetails();
        weatherDetail.setPostalCode("90210");
        weatherDetail.setUserName("Shyam");

        when(weatherDetailsRepository.findByPostalCodeAndUserName("90210", "Shyam"))
                .thenReturn(Arrays.asList(weatherDetail));

        ResponseEntity<?> response = weatherDetailsService.getWeatherHistory("90210", "Shyam");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
