package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegacyContactDto {
    private String telefonoFijo1;         // p_TelFijo1
    private String telefonoFijo2;         // p_TelFijo2
    private String telefonoCelular1;      // p_TelCel1
    private String telefonoCelular2;      // p_TelCel2
    private String emailPersonal;         // p_EmailPer
    private String emailTrabajo;          // p_EmailTra
    private String fax;                   // p_Fax
}