package com.fisa.bian.partyserviceoperation.infrastructure.config;

import com.fisa.bian.partyserviceoperation.application.services.PartyService;
import com.fisa.bian.partyserviceoperation.application.usecases.CreatePartyUseCaseImpl;
import com.fisa.bian.partyserviceoperation.domain.ports.in.CreatePartyUseCase;
import com.fisa.bian.partyserviceoperation.domain.ports.out.LegacySystemPort;
import com.fisa.bian.partyserviceoperation.domain.ports.out.PartyRepositoryPort;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.JpaPartyRepositoryAdapter;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.RestLegacySystemAdapter;
import com.fisa.bian.partyserviceoperation.infrastructure.adapters.output.legacy.mappers.LegacyIntegrationMapper;
import com.fisa.bian.partyserviceoperation.infrastructure.mappers.PartyInfraMapper;
import com.fisa.bian.partyserviceoperation.infrastructure.repositories.JpaPartyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfig {

    // 1. Wiring del Adaptador de Persistencia (Manual)
    // Se inyectan JpaPartyRepository y PartyInfraMapper que Spring genera automáticamente
    @Bean
    public PartyRepositoryPort partyRepositoryPort(JpaPartyRepository jpaRepository, PartyInfraMapper mapper) {
        return new JpaPartyRepositoryAdapter(jpaRepository, mapper);
    }

    // 2. Wiring del Adaptador Legacy (Manual)
    @Bean
    public LegacySystemPort legacySystemPort(RestClient.Builder restClientBuilder, LegacyIntegrationMapper mapper) {
        return new RestLegacySystemAdapter(restClientBuilder, mapper);
    }

    // 3. Wiring del Caso de Uso (Manual)
    @Bean
    public CreatePartyUseCase createPartyUseCase(PartyRepositoryPort repositoryPort, LegacySystemPort legacyPort) {
        return new CreatePartyUseCaseImpl(repositoryPort, legacyPort);
    }

    // 4. Wiring del Servicio Fachada (Manual)
    @Bean
    public PartyService partyService(CreatePartyUseCase createPartyUseCase) {
        return new PartyService(createPartyUseCase);
    }
}