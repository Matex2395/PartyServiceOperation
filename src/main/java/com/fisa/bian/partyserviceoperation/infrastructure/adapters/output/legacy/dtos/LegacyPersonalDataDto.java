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
public class LegacyPersonalDataDto {
    // 1. Identificación
    private String tipoIdentificacion;      // p_Tipoid
    private String numeroIdentificacion;    // p_rut
    private LocalDate fechaVencimientoID;   // p_fecvencid
    private String paisEmisor;              // p_codpaisid
    private String sexo;                    // p_sexo

    // 2. Nombres y Apellidos
    private String nombre;                  // p_nombre
    private String apellidoPaterno;         // p_appaterno
    private String apellidoMaterno;         // p_apmaterno
    private String estadoCivil;             // p_estadocivil
    private String tipoPersona;             // p_Tipoper

    // 3. Datos de Nacimiento
    private LocalDate fechaNacimiento;      // p_fecnac
    private String nacionalidad;            // p_nacionalidad
    private String nivelEstudio;            // p_nivelestudio
    private String profesion;               // p_profesion

    // 4. Otros
    private String tipoCliente;             // p_Tipcli
}