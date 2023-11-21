package za.co.discovery.assignment.thabomatjuda.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlanetQueryServiceTest {

    @Autowired
    private PlanetQueryService planetQueryService;



    @Test
    void shouldFetchAll() throws Exception {
        List<PlanetModel> planetModels = planetQueryService.fetchAll();
        assertTrue( CollectionUtils.isNotEmpty( planetModels));
    }

    @Test
    void shouldFetchById() throws Exception {
        PlanetModel foundRecord = planetQueryService.fetchById("C");
        assertNotNull( foundRecord);
        assertNotNull( foundRecord.getPlanetName());
        assertEquals( "Jupiter", foundRecord.getPlanetName());
    }

}