package com.reznikov.smartenergycustomer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor


@Data
public class AddressDto {
    @NotNull(message = "Latitude is mandatory")
    private double latitude;

    @NotNull(message = "Longitude is mandatory")
    private double longitude;

    @NotBlank(message = "City is mandatory")
    private String city;
}