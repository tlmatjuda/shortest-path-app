package com.toob.service.shortest.repository;

import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.service.SupportDataFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SystemStubsExtension.class)
public abstract class AbstractRepositoryTest {

    public static final String ENV_VAR_TESTCONTAINERS_RYUK_DISABLED = "TESTCONTAINERS_RYUK_DISABLED";
    public static final String TEST_DOCKER_CONTAINER_POSTGRES_IMAGE_NAME = "postgres:16-alpine3.19";

    @SystemStub
    protected static EnvironmentVariables ENV_VARS = new EnvironmentVariables(ENV_VAR_TESTCONTAINERS_RYUK_DISABLED, Boolean.TRUE.toString());

    @Container
    protected static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(TEST_DOCKER_CONTAINER_POSTGRES_IMAGE_NAME)
            .withDatabaseName("integration-tests-db")
            .withUsername("username")
            .withPassword("password");

    @MockBean
    protected StartupProcesses startupProcesses;

    @MockBean
    protected CalculatorService calculatorService;

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @Autowired
    protected PlanetRepository planetRepository;

    @BeforeEach
    protected void setUp() {
        Planet earth = new Planet();
        earth.setNode("A");
        earth.setName("Earth");
        Planet savedPlanetEarth = planetRepository.save(earth);
        assertNotNull( savedPlanetEarth);
        assertNotNull( savedPlanetEarth.getNode());
        assertNotNull( savedPlanetEarth.getName());

        Planet moon = new Planet();
        moon.setNode("B");
        moon.setName("Moon");
        Planet savedMoon = planetRepository.save(moon);
        assertNotNull( savedMoon);
        assertNotNull( savedMoon.getNode());
        assertNotNull( savedMoon.getName());
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);
    }

}
