package com.fisa.bian.partyserviceoperation.domain.models;

import java.util.UUID;

public record Demographics(
        String instanceId,
        String educationLevel,
        String occupationCode,
        String maritalStatusCode
) {
    public static Demographics createNew(String educationLevel, String occupationCode, String maritalStatusCode) {
        return new Demographics(
                UUID.randomUUID().toString(),
                educationLevel,
                occupationCode,
                maritalStatusCode
        );
    }
}