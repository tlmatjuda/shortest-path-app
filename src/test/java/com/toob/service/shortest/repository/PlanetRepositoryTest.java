package com.toob.service.shortest.repository;

import com.toob.service.shortest.entity.Planet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class PlanetRepositoryTest extends AbstractRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = buildPostgreSQLContainer();

    @Test
    void shouldFindPlanets() {
        List<Planet> planets = planetRepository.findAll();
        assertNotNull( planets);
    }

}