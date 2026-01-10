package com.fisa.bian.partyserviceoperation.domain.models;

import java.time.LocalDate;
import java.util.UUID;

public record ReferenceData(
        String instanceId,      // ID propio del BQ
        String identityNumber,  // RUT
        String identityType,    // 'RUT', 'PASAPORTE'
        String fullLegalName,
        LocalDate dateOfBirth,
        String emailAddress,
        String phoneNumber,
        String addressLine,
        String townName,
        String postCode,
        // -------------------------------
        String countryCode
) {
    // Factory actualizado para recibir los nuevos parámetros
    public static ReferenceData createNew(
            String identityNumber,
            String identityType,
            String fullLegalName,
            LocalDate dateOfBirth,
            String emailAddress,
            String phoneNumber,
            String addressLine,
            String townName,
            String postCode,
            String countryCode) {

        return new ReferenceData(
                UUID.randomUUID().toString(), // Generamos UUID del BQ aquí
                identityNumber,
                identityType,
                fullLegalName,
                dateOfBirth,
                emailAddress,
                phoneNumber,
                addressLine,
                townName,
                postCode,
                countryCode
        );
    }
}