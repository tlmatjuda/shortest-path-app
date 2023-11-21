package za.co.discovery.assignment.thabomatjuda.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.util.TestDataUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;


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
        PlanetModel planetModel = planetService.fetchById(venusId);
        assertNull( planetModel);
    }
}