package za.co.discovery.assignment.thabomatjuda.repository;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        route.setId(1);
        route.setOrigin( optionalEarth.get());
        route.setDestination( optionalMoon.get());
        route.setDistance(0.44D);

        Route save = routeRepository.save(route);
        assertNotNull( save);
    }
}