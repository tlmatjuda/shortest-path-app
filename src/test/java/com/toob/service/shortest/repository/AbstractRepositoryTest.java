package com.toob.service.shortest.repository;

import com.toob.service.shortest.AbstractSpringIntegrationTest;
import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.service.RouteService;
import com.toob.service.shortest.service.SupportDataFileService;
import com.toob.service.shortest.util.MockedPlanetsUtil;
import com.toob.service.shortest.util.MockedRoutesUtil;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@ActiveProfiles("test-containers")
public abstract class AbstractRepositoryTest extends AbstractSpringIntegrationTest {

    @MockBean
    protected CalculatorService calculatorService;

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @MockBean
    protected Flyway flyway;

    @Autowired
    protected PlanetRepository planetRepository;

    @Autowired
    protected RouteRepository routeRepository;

    @BeforeEach
    protected void setUp() {
        planetRepository.saveAll(MockedPlanetsUtil.fetchAll());
        routeRepository.saveAll(MockedRoutesUtil.fetchAll());
    }

}
