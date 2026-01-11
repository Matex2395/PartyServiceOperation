package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartyCreationRequest {

    @Valid
    @NotNull(message = "Reference data is required")
    private ReferenceDataDto referenceData;

    @Valid
    @NotNull(message = "Demographics data is required")
    private DemographicsDto demographics;
}