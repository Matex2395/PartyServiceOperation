package com.fisa.bian.partyserviceoperation.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "CR_DIRECTORY_ENTRY", schema = "BIAN_PARTY_DIR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrDirectoryEntryEntity {

    @Id
    @Column(name = "PARTY_REF_ID", length = 36)
    private String partyRefId; // UUID generado en Dominio

    @Column(name = "PARTY_TYPE", nullable = false, length = 20)
    private String partyType;

    @Column(name = "RECORD_STATUS", nullable = false, length = 20)
    private String recordStatus;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    // --- RELACIONES (HIJOS) ---

    // Relación 1 a 1 con BQ_REFERENCE
    @OneToOne(mappedBy = "directoryEntry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BqReferenceEntity referenceEntity;

    // Relación 1 a 1 con BQ_DEMOGRAPHICS
    @OneToOne(mappedBy = "directoryEntry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BqDemographicsEntity demographicsEntity;
}