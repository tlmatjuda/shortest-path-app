package za.co.discovery.assignment.thabomatjuda.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import za.co.discovery.assignment.thabomatjuda.util.TestDataUtil;
import za.co.discovery.assignment.thabomatjuda.config.BaseTestConfiguration;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;


    @Test
    void shouldFetchAll() throws Exception {
        List<PlanetModel> planetModels = planetService.fetchAll();
        assertTrue( CollectionUtils.isNotEmpty( planetModels));
        assertEquals( 37, planetModels.size());
    }

    @Test
    void shouldSave() throws Exception {
        PlanetModel planetModel = TestDataUtil.buildPlanet();
        PlanetModel save = planetService.save(planetModel);
        assertNotNull( save);
    }

    @Test
    void shouldFetchById() throws Exception {
        PlanetModel foundRecord = planetService.fetchById("C");
        assertNotNull( foundRecord);
        assertNotNull( foundRecord.getPlanetName());
        assertEquals( "Jupiter", foundRecord.getPlanetName());
    }

    @Test
    void shouldDeleteById() {
        final String venusId = "D";
        planetService.deleteById(venusId);
        PlanetModel planetModel = planetService.fetchById(venusId);
        assertNull( planetModel);
    }
}