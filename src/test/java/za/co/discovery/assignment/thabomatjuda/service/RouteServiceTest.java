package za.co.discovery.assignment.thabomatjuda.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.entity.Route;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RouteServiceTest {

    @Autowired
    private RouteService routeService;


    @Test
    void shouldFetchByPlanetOriginAndPlanetDestination() {
        final String earthRoutes = "A";
        List<Route> routes = routeService.fetchByPlanetOriginAndPlanetDestination(earthRoutes, earthRoutes);
        assertTrue(CollectionUtils.isNotEmpty( routes));
    }
}