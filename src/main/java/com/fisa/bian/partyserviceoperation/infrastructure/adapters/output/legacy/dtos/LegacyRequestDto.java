package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegacyRequestDto {
    private LegacyPersonalDataDto datosPersonales;
    private LegacyAddressDto datosDomicilio;
    private LegacyLaborDataDto datosLaborales;       // Puede ser null
    private LegacyContactDto datosContacto;
    private LegacyAdminDataDto datosAdministrativos;
}