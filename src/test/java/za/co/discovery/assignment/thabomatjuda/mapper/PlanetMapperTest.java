package za.co.discovery.assignment.thabomatjuda.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class PlanetMapperTest {

    @Autowired
    private PlanetMapper planetMapper;

    @Autowired
    private PlanetRepository planetRepository;


    @Test
    void shouldMap() {
        Planet planet = new Planet();
        planet.setPlanetNode("A");
        planet.setPlanetName("Earth");

        PlanetModel model = planetMapper.asModel(planet);
        assertNotNull( model);
        assertNotNull( model.getPlanetNode());
        assertEquals( planet.getPlanetNode(), model.getPlanetNode());
        assertNotNull( model.getPlanetName());
        assertEquals( planet.getPlanetName(), model.getPlanetName());
    }

    @Test
    void shouldMapCollection() {
        List<Planet> planets = planetRepository.findAll();
        assertTrue(CollectionUtils.isNotEmpty( planets));

        List<PlanetModel> modelList = planetMapper.asModel(planets);
        assertTrue( CollectionUtils.isNotEmpty( modelList));

        assertEquals( planets.size(), modelList.size());
    }
}