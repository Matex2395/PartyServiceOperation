package com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy;

import com.fisa.bian.partyserviceoperation.domain.models.Party;
import com.fisa.bian.partyserviceoperation.domain.ports.out.LegacySystemPort;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.dtos.LegacyRequestDto;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.mappers.LegacyIntegrationMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class RestLegacySystemAdapter implements LegacySystemPort {

//    private final RestClient.Builder restClientBuilder;
//    private final LegacyIntegrationMapper mapper;
//
//    @Value("${legacy.api.url}")
//    private String legacyApiUrl;

    @Override
//    @CircuitBreaker(name = "legacyCore", fallbackMethod = "fallbackSynchronize")
    public boolean synchronizeCustomer(Party party) {
        // --- MODO DE PRUEBA: SIMULACIÓN ---
        log.info("[MOCK] Saltando llamada al Core Legacy para PartyRefID: {}", party.partyRefId());
        log.info("[MOCK] Simulando respuesta EXITOSA (HTTP 200) del Core.");

        return true; // <--- Esto permite que el flujo continúe hacia la Base de Datos BIAN

//        log.info("ADAPTER: Iniciando llamada POST al Core Legacy para PartyRefID: {}", party.partyRefId());
//
//        try {
//            LegacyRequestDto request = mapper.toLegacyRequest(party);
//
//            var response = restClientBuilder.baseUrl(legacyApiUrl).build()
//                    .post()
//                    .uri("/api/v1/clientes")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(request)
//                    .retrieve()
//                    .toBodilessEntity();
//
//            boolean success = response.getStatusCode().is2xxSuccessful();
//            log.info("ADAPTER: Respuesta del Core recibida. Éxito: {}", success);
//            return success;
//
//        } catch (Exception e) {
//            // Si ocurre un error de conexión (ej. Connection Refused), lanzamos la excepción
//            // para que Resilience4j la cuente como falla y active el Circuit Breaker.
//            log.error("ADAPTER: Error técnico llamando al Core: {}", e.getMessage());
//            throw e;
//        }
    }

    // --- FALLBACK ---
    // Este método se ejecuta cuando el Circuit Breaker está ABIERTO (Open State)
    // o cuando la llamada falla por Timeout/Error.
    public boolean fallbackSynchronize(Party party, Throwable t) {
        log.warn("CIRCUIT BREAKER ACTIVADO: No se pudo contactar al Core Legacy. Razón: {}", t.getMessage());

        // Retornamos false.
        // Esto le indica al Caso de Uso que la sincronización falló.
        // El Caso de Uso entonces lanzará la excepción de negocio y NO guardará en BIAN DB.
        return false;
    }
}