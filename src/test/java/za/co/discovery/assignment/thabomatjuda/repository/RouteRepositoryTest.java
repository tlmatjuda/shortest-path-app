package za.co.discovery.assignment.thabomatjuda.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.entity.Route;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RouteRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;


    @Test
    void shouldSaveRecord() {

        Optional<Planet> optionalEarth = planetRepository.findById("A");
        assertTrue( optionalEarth.isPresent());

        Optional<Planet> optionalMoon = planetRepository.findById("B");
        assertTrue( optionalMoon.isPresent());

        Route route = new Route();
        route.setRouteId(1);
        route.setPlanetOrigin( optionalEarth.get());
        route.setPlanetDestination( optionalMoon.get());
        route.setDistanceInLightYears(0.44D);

        Route save = routeRepository.save(route);
        assertNotNull( save);
    }
}