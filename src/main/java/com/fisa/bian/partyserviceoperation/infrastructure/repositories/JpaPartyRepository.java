package com.fisa.bian.partyserviceoperation.infrastructure.repositories;

import com.fisa.bian.partyserviceoperation.infrastructure.entities.CrDirectoryEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPartyRepository extends JpaRepository<CrDirectoryEntryEntity, String> {

    // Consulta personalizada para validar unicidad (Regla BIAN: Un cliente = Una Identidad)
    // Se realiza un JOIN con la tabla hija BQ_REFERENCE
    @Query("""
        SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END
        FROM CrDirectoryEntryEntity c
        JOIN c.referenceEntity r
        WHERE r.identityNumber = :identityNumber AND r.identityType = :identityType
    """)
    boolean existsByIdentity(String identityNumber, String identityType);
}