package com.toob.service.shortest.service;

import com.toob.service.shortest.model.planet.PlanetModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.toob.service.shortest.util.TestDataUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PlanetQueryService planetQueryService;


    @Test
    void shouldSave() throws Exception {
        PlanetModel planetModel = TestDataUtil.buildPlanet();
        PlanetModel save = planetService.save(planetModel);
        assertNotNull( save);
    }


    @Test
    void shouldDeleteById() {
        final String venusId = "D";
        planetService.deleteById(venusId);
        PlanetModel planetModel = planetQueryService.fetchById(venusId);
        assertNull( planetModel);
    }
}