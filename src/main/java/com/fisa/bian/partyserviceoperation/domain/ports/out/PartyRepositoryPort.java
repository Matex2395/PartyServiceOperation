package com.fisa.bian.partyserviceoperation.domain.ports.out;

import com.fisa.bian.partyserviceoperation.domain.models.Party;
import java.util.Optional;

public interface PartyRepositoryPort {

    // Guardar el Agregado completo (CR + BQs)
    Party save(Party party);

    // Buscar por ID (útil para validaciones futuras)
    Optional<Party> findById(String partyRefId);

    // Verificar si ya existe por Identificación Legal (Regla de Unicidad)
    boolean existsByIdentity(String identityNumber, String identityType);
}