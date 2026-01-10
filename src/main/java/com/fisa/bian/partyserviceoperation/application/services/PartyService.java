package com.fisa.bian.partyserviceoperation.application.services;

import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.ports.in.CreatePartyUseCase;

public class PartyService implements CreatePartyUseCase {

    private final CreatePartyUseCase createPartyUseCase;

    // Aquí se inyectará: UpdatePartyUseCase, RetrievePartyUseCase, etc.

    public PartyService(CreatePartyUseCase createPartyUseCase) {
        this.createPartyUseCase = createPartyUseCase;
    }

    @Override
    public Party createParty(Party party) {
        return createPartyUseCase.createParty(party);
    }
}