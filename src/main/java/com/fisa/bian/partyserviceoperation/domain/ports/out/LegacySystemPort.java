package com.fisa.bian.partyserviceoperation.domain.ports.out;

import com.fisa.bian.partyserviceoperation.domain.models.Party;

public interface LegacySystemPort {

    /**
     * Envía los datos del cliente al sistema Legacy (Core Bancario)
     * a través del Mapper.
     * Retorna true si el Core aceptó la creación.
     */
    boolean synchronizeCustomer(Party party);
}