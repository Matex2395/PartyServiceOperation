package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Nationality Code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Nationality must be ISO Alpha-2 (2 chars)")
    private String nationalityCode;

    @NotBlank(message = "Town Name is required")
    private String townName;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country Code must be ISO Alpha-2 (2 chars)")
    private String countryCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate identityExpiryDate; // Opcional en BD

    @Pattern(regexp = "^[M|F|O]$", message = "Gender must be M, F or O")
    private String genderCode; // Opcional en BD

    private String addressLine; // Opcional en BD
    private String postCode;    // Opcional en BD

    @Email(message = "Invalid email format")
    private String emailAddress; // Opcional en BD

    private String phoneNumber;  // Opcional en BD
}