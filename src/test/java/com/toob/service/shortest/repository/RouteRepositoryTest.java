package com.toob.service.shortest.repository;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RouteRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @Test
    void shouldSaveRecord() {
        Optional<Planet> origin = planetRepository.findById("A");
        assertTrue( origin.isPresent());

        Optional<Planet> destination = planetRepository.findById("B");
        assertTrue( destination.isPresent());

        Route route = new Route();
        route.setId(1);
        route.setOrigin( origin.get());
        route.setDestination( destination.get());
        route.setDistance(0.44D);

        Route saved = routeRepository.save(route);
        assertNotNull( saved);
    }

    @Test
    void shouldDeleteRoutesByPlanetOriginAndPlanetDestination() {
        shouldSaveRecord();
        List<Route> foundRoutes = routeRepository.findByPlanetOriginAndPlanetDestination("A", "B");
        assertTrue(CollectionUtils.isNotEmpty( foundRoutes));

        Route route = foundRoutes.get(0);

        assertNotNull( route);
        assertNotNull( route.getOrigin());
        assertNotNull( route.getOrigin().getNode());
        assertNotNull( route.getDestination());
        assertNotNull( route.getDestination().getNode());

        routeRepository.deleteRoutesByPlanetOriginAndPlanetDestination(
                route.getOrigin().getNode(), route.getDestination().getNode());

        List<Route> routesShouldBeEmpty = routeRepository.findByPlanetOriginAndPlanetDestination("A", "B");
        assertTrue(CollectionUtils.isEmpty( routesShouldBeEmpty));
    }
}