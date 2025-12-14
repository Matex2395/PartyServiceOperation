package com.fisa.bian.partyserviceoperation.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BQ_DEMOGRAPHICS", schema = "BIAN_PARTY_DIR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BqDemographicsEntity {

    @Id
    @Column(name = "DEMO_INSTANCE_ID", length = 36)
    private String demoInstanceId;

    @OneToOne
    @JoinColumn(name = "PARTY_REF_ID", referencedColumnName = "PARTY_REF_ID", nullable = false)
    private CrDirectoryEntryEntity directoryEntry;

    @Column(name = "EDUCATION_LEVEL", length = 50)
    private String educationLevel;

    @Column(name = "OCCUPATION_CODE", length = 50)
    private String occupationCode;

    @Column(name = "MARITAL_STATUS_CODE", length = 20)
    private String maritalStatusCode;
}