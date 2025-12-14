package com.fisa.bian.partyserviceoperation.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record Party(
        String partyRefId,          // El ID principal del cliente (CR)
        String partyType,           // 'Person' o 'Organisation'
        String recordStatus,        // 'ACTIVE'
        LocalDateTime creationDate,

        // Relación con los BQ (Hijos)
        ReferenceData referenceData,
        Demographics demographics
        // Puedes agregar 'associations' aquí si lo necesitas a futuro
) {

    /**
     * FACTORY METHOD: Punto de entrada único para crear un cliente.
     * Genera la identidad (UUID) del padre y orquesta la de los hijos.
     */
    public static Party createNewPerson(
            ReferenceData referenceData,
            Demographics demographics) {

        return new Party(
                UUID.randomUUID().toString(), // 1. Generamos ID del Cliente
                "Person",                     // 2. Definimos tipo fijo por regla de negocio
                "ACTIVE",
                LocalDateTime.now(),
                referenceData,                // 3. Asociamos los datos ya creados (que traen sus propios UUIDs)
                demographics
        );
    }
}