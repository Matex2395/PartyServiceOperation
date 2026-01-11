package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos;

import lombok.Data;

@Data
public class DemographicsDto {
    private String educationLevel;
    private String occupationCode;
    private String maritalStatusCode;
}