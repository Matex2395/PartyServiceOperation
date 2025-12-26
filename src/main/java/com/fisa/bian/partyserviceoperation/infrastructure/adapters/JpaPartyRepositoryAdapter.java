package com.fisa.bian.partyserviceoperation.infrastructure.adapters;

import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.ports.out.PartyRepositoryPort;
import com.fisa.bian.partyserviceoperation.infrastructure.entities.CrDirectoryEntryEntity;
import com.fisa.bian.partyserviceoperation.infrastructure.mappers.PartyInfraMapper;
import com.fisa.bian.partyserviceoperation.infrastructure.repositories.JpaPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor // Lombok genera el constructor para inyección de dependencias
public class JpaPartyRepositoryAdapter implements PartyRepositoryPort {

    private final JpaPartyRepository jpaRepository;
    private final PartyInfraMapper mapper;

    @Override
    public Party save(Party party) {
        // 1. Convertir Dominio -> Entidad JPA
        CrDirectoryEntryEntity entity = mapper.toEntity(party);

        // 2. Gestión de Relaciones Bidireccionales (Esencial para JPA)
        // El padre conoce a los hijos, pero los hijos necesitan conocer al padre antes de guardar
        if (entity.getReferenceEntity() != null) {
            entity.getReferenceEntity().setDirectoryEntry(entity);
        }
        if (entity.getDemographicsEntity() != null) {
            entity.getDemographicsEntity().setDirectoryEntry(entity);
        }

        // 3. Guardar en SQL Server
        CrDirectoryEntryEntity savedEntity = jpaRepository.save(entity);

        // 4. Retornar Dominio actualizado
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Party> findById(String partyRefId) {
        return jpaRepository.findById(partyRefId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByIdentity(String identityNumber, String identityType) {
        return jpaRepository.existsByIdentity(identityNumber, identityType);
    }
}