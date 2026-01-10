package com.fisa.bian.partyserviceoperation.application.usecases;

import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.ports.in.CreatePartyUseCase;
import com.fisa.bian.partyserviceoperation.domain.ports.out.LegacySystemPort;
import com.fisa.bian.partyserviceoperation.domain.ports.out.PartyRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePartyUseCaseImpl implements CreatePartyUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreatePartyUseCaseImpl.class);

    private final PartyRepositoryPort repositoryPort;
    private final LegacySystemPort legacyPort;

    public CreatePartyUseCaseImpl(PartyRepositoryPort repositoryPort, LegacySystemPort legacyPort) {
        this.repositoryPort = repositoryPort;
        this.legacyPort = legacyPort;
    }

    @Override
    public Party createParty(Party party) {
        // 1. Regla de Negocio: Verificar Unicidad antes de procesar
        if (repositoryPort.existsByIdentity(
                party.referenceData().identityNumber(),
                party.referenceData().identityType())) {
            throw new IllegalArgumentException("La Parte (Cliente) ya existe en el directorio BIAN.");
        }

        log.info("Iniciando creación para PartyRefID: {}. Intentando sincronizar con Core Legacy...", party.partyRefId());

        // 2. INTEGRACIÓN PRIMERO: Intentar sincronizar con Core Bancario
        // Al generar los UUIDs en memoria (en el Dominio), podemos enviarlos al Core antes de guardar en nuestra BD.
        boolean synced = legacyPort.synchronizeCustomer(party);

        if (!synced) {
            log.error("FALLO CRÍTICO: No se pudo sincronizar con el Core Bancario. Se cancela la creación en BIAN DB.");
            throw new RuntimeException("Error: El Core Bancario no respondió correctamente. Transacción cancelada.");
        }

        // 3. PERSISTENCIA: Solo si el paso anterior fue exitoso, guardamos en SQL Server
        Party savedParty = repositoryPort.save(party);
        log.info("ÉXITO: Party sincronizada con Core y guardada en BIAN Directory con ID: {}", savedParty.partyRefId());

        return savedParty;
    }
}