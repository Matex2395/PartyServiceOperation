package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.mappers;

import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.models.ReferenceData; // Importante importar esto
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LegacyIntegrationMapper {

    @Mapping(target = "datosPersonales", source = "party", qualifiedByName = "mapPersonalData")
    @Mapping(target = "datosDomicilio", source = "party.referenceData", qualifiedByName = "mapAddress")
    @Mapping(target = "datosContacto", source = "party.referenceData", qualifiedByName = "mapContact")
    @Mapping(target = "datosAdministrativos", expression = "java(mapDefaultAdminData())")
    @Mapping(target = "datosLaborales", ignore = true)
    LegacyRequestDto toLegacyRequest(Party party);

    // --- Mapeos Específicos ---

    @Named("mapPersonalData")
    default LegacyPersonalDataDto mapPersonalData(Party party) {
        // CORRECCIÓN: Usamos el tipo explícito en lugar de 'var'
        ReferenceData ref = party.referenceData();

        return LegacyPersonalDataDto.builder()
                .numeroIdentificacion(ref.identityNumber())
                .tipoIdentificacion(ref.identityType())
                .nombre(ref.fullLegalName())
                .fechaNacimiento(ref.dateOfBirth())
                .tipoPersona("1") // Valor quemado ejemplo
                .build();
    }

    @Named("mapAddress")
    default LegacyAddressDto mapAddress(ReferenceData refData) {
        return LegacyAddressDto.builder()
                .pais(refData.countryCode())
                .calle(refData.addressLine())
                .ciudad(refData.townName())
                .codigoPostal(refData.postCode())
                .build();
    }

    @Named("mapContact")
    default LegacyContactDto mapContact(ReferenceData refData) {
        return LegacyContactDto.builder()
                .emailPersonal(refData.emailAddress())
                .telefonoCelular1(refData.phoneNumber())
                .build();
    }

    default LegacyAdminDataDto mapDefaultAdminData() {
        return LegacyAdminDataDto.builder()
                .canal("ONLINE")
                .operador("JUAN PÉREZ")
                .sucursal(999) // Valor por definirse
                .oficina(999) // Valor por definirse
                .sistemaOrigen(10) // Valor por definirse
                .build();
    }
}