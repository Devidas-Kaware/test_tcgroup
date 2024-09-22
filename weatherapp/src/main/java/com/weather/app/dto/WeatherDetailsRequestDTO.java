package com.weather.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class WeatherDetailsRequestDTO {

    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Postal code must be a valid US ZIP code")
    private String postalCode;

    // User name must not be blank
    @NotBlank(message = "User name is required")
    private String userName;

}
