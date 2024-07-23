package com.toob.service.shortest;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.util.MockedPlanetsUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@Testcontainers
public class PlanetsDataAccessTest extends BaseRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine3.19");

    @Autowired
    PlanetRepository planetRepository;

    @Test
    void shouldValidateConnection() {
        assertTrue( postgres.isCreated());
        assertTrue( postgres.isRunning());
    }

    @Test
    void shouldFetchAllPlanets() {
        List<Planet> entities = MockedPlanetsUtil.fetchAll();
        planetRepository.saveAll(entities);
        List<Planet> planets = planetRepository.findAll();
        assertNotNull(planets);
    }
}
