package com.toob.service.shortest.repository;

import com.toob.service.shortest.BaseTestConfiguration;
import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.service.CalculationService;
import com.toob.service.shortest.service.SupportDataFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@ExtendWith(SystemStubsExtension.class)
public abstract class AbstractDataAccessTest {

    @SystemStub
    private static EnvironmentVariables ENV_VARS = new EnvironmentVariables(
            "TESTCONTAINERS_RYUK_DISABLED", "true");

    @MockBean
    protected StartupProcesses startupProcesses;

    @MockBean
    protected CalculationService calculationService;

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
}
