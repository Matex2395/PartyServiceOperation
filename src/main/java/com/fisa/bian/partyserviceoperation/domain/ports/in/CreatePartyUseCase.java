package com.fisa.bian.partyserviceoperation.domain.ports.in;

import com.fisa.bian.partyserviceoperation.domain.models.Party;

public interface CreatePartyUseCase {
    /**
     * Recibe un Party (ya construido con reglas de dominio) y orquesta su persistencia.
     */
    Party createParty(Party party);
}