package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DemographicsDto {

    @NotBlank(message = "Education Level is required")
    private String educationLevel;

    @NotBlank(message = "Occupation Code is required")
    private String occupationCode;

    @NotBlank(message = "Marital Status Code is required")
    private String maritalStatusCode;
}