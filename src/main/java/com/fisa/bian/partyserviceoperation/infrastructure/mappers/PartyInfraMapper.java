package com.fisa.bian.partyserviceoperation.infrastructure.mappers;

import com.fisa.bian.partyserviceoperation.domain.models.Demographics;
import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.models.ReferenceData;
import com.fisa.bian.partyserviceoperation.infrastructure.entities.BqDemographicsEntity;
import com.fisa.bian.partyserviceoperation.infrastructure.entities.BqReferenceEntity;
import com.fisa.bian.partyserviceoperation.infrastructure.entities.CrDirectoryEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartyInfraMapper {

    // --- Mapeo de ROOT (Party -> Entity) ---
    @Mapping(target = "referenceEntity", source = "referenceData")
    @Mapping(target = "demographicsEntity", source = "demographics")
    @Mapping(target = "lastUpdateDate", ignore = true) // Se gestiona en updates
    CrDirectoryEntryEntity toEntity(Party party);

    // Mapeo inverso (Entity -> Party) para lecturas
    @Mapping(target = "referenceData", source = "referenceEntity")
    @Mapping(target = "demographics", source = "demographicsEntity")
    Party toDomain(CrDirectoryEntryEntity entity);


    // --- Mapeos de HIJOS (Llamados automáticamente por MapStruct) ---

    // 1. ReferenceData -> BqReferenceEntity
    @Mapping(target = "refInstanceId", source = "instanceId")
    @Mapping(target = "directoryEntry", ignore = true) // Se asigna en el código Java (relación bidireccional)
    @Mapping(target = "identityExpiryDate", ignore = true) // TODO: Añadir al dominio si hace falta
    @Mapping(target = "nationalityCode", ignore = true)
    @Mapping(target = "genderCode", ignore = true)
    @Mapping(target = "townName", ignore = true)
    @Mapping(target = "postCode", ignore = true)
    BqReferenceEntity toReferenceEntity(ReferenceData data);

    @Mapping(target = "instanceId", source = "refInstanceId")
    ReferenceData toReferenceDomain(BqReferenceEntity entity);


    // 2. Demographics -> BqDemographicsEntity
    @Mapping(target = "demoInstanceId", source = "instanceId")
    @Mapping(target = "directoryEntry", ignore = true)
    BqDemographicsEntity toDemographicsEntity(Demographics data);

    @Mapping(target = "instanceId", source = "demoInstanceId")
    Demographics toDemographicsDomain(BqDemographicsEntity entity);
}