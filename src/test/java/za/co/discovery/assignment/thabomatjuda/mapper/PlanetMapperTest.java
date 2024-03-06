package za.co.discovery.assignment.thabomatjuda.mapper;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.mapper.PlanetMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlanetMapperTest {

    @Autowired
    private PlanetMapper planetMapper;

    @Autowired
    private PlanetRepository planetRepository;


    @Test
    void shouldMap() {
        Optional<Planet> optionalPlanet = planetRepository.findById("A");
        assertTrue( optionalPlanet.isPresent());

        Planet planet = optionalPlanet.get();
        PlanetModel model = planetMapper.asModel(planet);

        assertNotNull( model);
        assertEquals( planet.getPlanetNode(), model.getPlanetNode());
        assertEquals( planet.getPlanetName(), model.getPlanetName());

        Planet entity = planetMapper.asEntity(model);
        assertNotNull( entity);
    }

    @Test
    void shouldMapCollection() {
        List<Planet> planets = planetRepository.findAll();
        assertTrue(CollectionUtils.isNotEmpty( planets));

        List<PlanetModel> modelList = planetMapper.asModel(planets);
        assertTrue( CollectionUtils.isNotEmpty( modelList));

        assertEquals( planets.size(), modelList.size());
        List<Planet> entityList = planetMapper.asEntity(modelList);
        assertTrue( CollectionUtils.isNotEmpty( entityList));
    }
}