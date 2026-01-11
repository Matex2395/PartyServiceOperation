package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.mappers;

import com.fisa.bian.partyserviceoperation.domain.models.Demographics;
import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.models.ReferenceData;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos.DemographicsDto;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos.PartyCreationRequest;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos.ReferenceDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartyApiMapper {

    // Método principal: DTO -> Dominio (Usando el Factory Method)
    default Party toDomain(PartyCreationRequest request) {
        if (request == null) return null;

        return Party.createNewPerson(
                toReferenceDomain(request.getReferenceData()),
                toDemographicsDomain(request.getDemographics())
        );
    }

    // Sub-mapeo: Reference DTO -> Reference Record
    default ReferenceData toReferenceDomain(ReferenceDataDto dto) {
        if (dto == null) return null;

        return ReferenceData.createNew(
                dto.getIdentityNumber(),
                dto.getIdentityType(),
                dto.getFullLegalName(),
                dto.getDateOfBirth(),
                dto.getEmailAddress(),
                dto.getPhoneNumber(),
                dto.getAddressLine(),
                dto.getTownName(),
                dto.getPostCode(),
                dto.getCountryCode()
        );
    }

    // Sub-mapeo: Demographics DTO -> Demographics Record
    default Demographics toDemographicsDomain(DemographicsDto dto) {
        if (dto == null) return null;

        return Demographics.createNew(
                dto.getEducationLevel(),
                dto.getOccupationCode(),
                dto.getMaritalStatusCode()
        );
    }
}