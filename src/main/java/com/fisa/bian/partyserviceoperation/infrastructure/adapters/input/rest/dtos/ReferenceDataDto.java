package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ReferenceDataDto {
    @NotBlank(message = "Identity Number is required")
    private String identityNumber;

    @NotBlank(message = "Identity Type is required")
    private String identityType;

    @NotBlank(message = "Full Legal Name is required")
    private String fullLegalName;

    @NotNull(message = "Date of Birth is required") // @NotNull para objetos LocalDate
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Nationality Code is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Nationality must be ISO Alpha-3")
    private String nationalityCode;

    @NotBlank(message = "Town Name is required")
    private String townName;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Country Code must be 3 uppercase letters")
    private String countryCode;

    private LocalDate identityExpiryDate; // Opcional en BD

    @Pattern(regexp = "^[M|F|O]$", message = "Gender must be M, F or O")
    private String genderCode; // Opcional en BD

    private String addressLine; // Opcional en BD
    private String postCode;    // Opcional en BD

    @Email(message = "Invalid email format")
    private String emailAddress; // Opcional en BD

    private String phoneNumber;  // Opcional en BD
}