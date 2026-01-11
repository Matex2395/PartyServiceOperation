package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ReferenceDataDto {

    @NotBlank(message = "Identity Number (RUT/DNI) is required")
    private String identityNumber;

    @NotBlank(message = "Identity Type is required (e.g. RUT, PASSPORT)")
    private String identityType;

    @NotBlank(message = "Full Legal Name is required")
    private String fullLegalName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Email(message = "Invalid email format")
    private String emailAddress;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address line is required")
    private String addressLine;

    private String townName;  // Opcional
    private String postCode;  // Opcional

    @NotBlank(message = "Country code is required (ISO 3 chars)")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Country Code must be 3 uppercase letters (ISO 3166)")
    private String countryCode;
}