package com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest;

import com.fisa.bian.partyserviceoperation.application.services.PartyService;
import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos.PartyCreationRequest;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.mappers.PartyApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bian-party/v1")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;
    private final PartyApiMapper apiMapper;

    @PostMapping("/parties")
    public ResponseEntity<Party> createParty(@RequestBody @Valid PartyCreationRequest request) {

        // 1. Convertir JSON (DTO) -> Dominio (Record)
        Party domainParty = apiMapper.toDomain(request);

        // 2. Ejecutar Caso de Uso (Lógica de Negocio)
        Party createdParty = partyService.createParty(domainParty);

        // 3. Retornar Respuesta 201 Created
        return new ResponseEntity<>(createdParty, HttpStatus.CREATED);
    }

    // Endpoint simple para probar que el servicio está vivo
    @GetMapping("/health")
    public String healthCheck() {
        return "Service is UP";
    }
}