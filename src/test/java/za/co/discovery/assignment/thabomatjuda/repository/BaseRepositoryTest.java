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
        earth.setPlanetNode("A");
        earth.setPlanetName("Earth");
        Planet savedPlanetEarth = planetRepository.save(earth);
        assertNotNull( savedPlanetEarth);
        assertNotNull( savedPlanetEarth.getPlanetNode());
        assertNotNull( savedPlanetEarth.getPlanetName());


        Planet moon = new Planet();
        moon.setPlanetNode("B");
        moon.setPlanetName("Moon");
        Planet savedMoon = planetRepository.save(moon);
        assertNotNull( savedMoon);
        assertNotNull( savedMoon.getPlanetNode());
        assertNotNull( savedMoon.getPlanetName());
    }
}
