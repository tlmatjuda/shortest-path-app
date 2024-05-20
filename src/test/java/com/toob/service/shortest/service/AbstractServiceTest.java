package com.toob.service.shortest.service;

import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoConfiguration( exclude = {
        FlywayAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
public abstract class AbstractServiceTest {

    @MockBean
    protected PlanetRepository planetRepository;

    @MockBean
    protected RouteRepository routeRepository;

    @MockBean
    protected StartupProcesses startupProcesses;

    @MockBean
    protected CalculationService calculationService;

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @MockBean
    protected Flyway flyway;

    protected static Planet mockPlanet(String node, String name) {
        Planet planet = new Planet();
        planet.setNode(node);
        planet.setName(name);
        return planet;
    }

    protected static Route mockRoute(Integer id, Planet origin, Planet destination, Double distance) {
        Route route = new Route();
        route.setId(id);
        route.setOrigin(origin);
        route.setDestination(destination);
        route.setDistance(distance);
        return route;
    }

}
