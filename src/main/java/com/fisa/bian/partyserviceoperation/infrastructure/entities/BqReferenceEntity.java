package com.fisa.bian.partyserviceoperation.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "BQ_REFERENCE", schema = "BIAN_PARTY_DIR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BqReferenceEntity {

    @Id
    @Column(name = "REF_INSTANCE_ID", length = 36)
    private String refInstanceId;

    // Relación con el Padre (FK)
    @OneToOne
    @JoinColumn(name = "PARTY_REF_ID", referencedColumnName = "PARTY_REF_ID", nullable = false)
    private CrDirectoryEntryEntity directoryEntry;

    // Datos de Identidad
    @Column(name = "IDENTITY_NUMBER", nullable = false, length = 35)
    private String identityNumber;

    @Column(name = "IDENTITY_TYPE", nullable = false, length = 10)
    private String identityType;

    @Column(name = "IDENTITY_EXPIRY_DATE")
    private LocalDate identityExpiryDate;

    @Column(name = "FULL_LEGAL_NAME", nullable = false, length = 140)
    private String fullLegalName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "NATIONALITY_CODE", nullable = false, length = 2)
    private String nationalityCode;

    @Column(name = "GENDER_CODE", length = 1)
    private String genderCode;

    // Dirección
    @Column(name = "ADDRESS_LINE", length = 140)
    private String addressLine;

    @Column(name = "TOWN_NAME", nullable = false, length = 70)
    private String townName;

    @Column(name = "COUNTRY_CODE", nullable = false, length = 2)
    private String countryCode;

    @Column(name = "POST_CODE", length = 16)
    private String postCode;

    // Contacto
    @Column(name = "EMAIL_ADDRESS", length = 128)
    private String emailAddress;

    @Column(name = "PHONE_NUMBER", length = 35)
    private String phoneNumber;
}