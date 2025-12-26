package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegacyLaborDataDto {
    // Actividad y Contrato
    private String tipoActividad;     // p_tipactividad
    private String rutEmpresa;        // p_rutempresa
    private String nombreEmpresa;     // p_nomempresa
    private String tipoContrato;      // p_tipcontrato
    private String cargo;             // p_cargo
    private String giroEmpresa;       // p_giroempresa
    private LocalDate fechaIngresoEmpresa; // p_fecingemp

    // Dirección Laboral
    private String pais;              // p_pais
    private String calle;             // p_calle
    private String numeroCasa;        // p_numcasa
    private String complementoDireccion; // P_complemDir
    private String comuna;            // p_comuna
    private String ciudad;            // p_ciudad

    // Contacto Laboral
    private String telefonoFijo;      // p_TelFijo
    private Integer anexo;            // p_Anexo (Integer)
    private String fax;               // p_Fax
    private String telCelular;        // p_TelCelular
}