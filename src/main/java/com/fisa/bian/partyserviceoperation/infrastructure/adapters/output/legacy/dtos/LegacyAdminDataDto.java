package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegacyAdminDataDto {
    private String operador;      // p_operador
    private Integer sucursal;     // p_sucursal
    private Integer oficina;      // p_oficina
    private Integer sistemaOrigen;// p_sistema
    private String canal;         // p_canal
}