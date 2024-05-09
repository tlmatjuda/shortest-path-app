package za.co.discovery.assignment.thabomatjuda.repository;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public abstract class BaseRepositoryTest {


    @Autowired
    protected PlanetRepository planetRepository;


    @BeforeEach
    void setUp() {
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
