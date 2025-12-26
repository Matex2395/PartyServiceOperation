package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegacyAddressDto {
    private String pais;                  // p_CodPaisDom
    private String calle;                 // p_calle
    private String numeroCasa;            // p_numCasa
    private String complementoDireccion;  // p_complemDir
    private String comuna;                // p_comuna
    private String ciudad;                // p_ciudad
    private String codigoPostal;          // p_codpostal
    private Integer aniosResidencia;      // p_AnosResid (Integer)
}