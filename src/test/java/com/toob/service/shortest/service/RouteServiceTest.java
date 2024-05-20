package com.toob.service.shortest.service;

import com.google.common.collect.Lists;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.util.TestDataUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

@SpringBootTest
class RouteServiceTest extends AbstractServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PlanetService planetService;


    @Test
    @SneakyThrows
    void shouldFetchAll() {
        Planet earth = TestDataUtil.mockPlanet("A", "Earth");
        Planet moon = TestDataUtil.mockPlanet("B", "Moon");
        Planet jupiter = TestDataUtil.mockPlanet("C", "Jupiter");
        Planet venus = TestDataUtil.mockPlanet("D", "Venus");

        ArrayList<Route> mockedRoutes = Lists.newArrayList(
                TestDataUtil.mockRoute(1, earth, moon, 0.44D),
                TestDataUtil.mockRoute(2, earth, jupiter, 1.89D),
                TestDataUtil.mockRoute(3, earth, venus, 0.10D)
        );

        when( routeRepository.findAll()).thenReturn(mockedRoutes);

        List<RouteMinimalModel> routeModels = routeService.fetchAll();
        assertTrue( CollectionUtils.isNotEmpty( routeModels));
    }

    @Test
    @SneakyThrows
    void shouldSave() {
        Planet earth = TestDataUtil.mockPlanet("A", "Earth");
        Planet jupiter = TestDataUtil.mockPlanet("C", "Jupiter");

        when( planetRepository.findById( earth.getNode())).thenReturn( Optional.of( earth));
        when( planetRepository.findById( jupiter.getNode())).thenReturn( Optional.of( jupiter));

        RouteMinimalModel routeMinimalModel = new RouteMinimalModel();
        routeMinimalModel.setId(2);
        routeMinimalModel.setOrigin( earth.getNode());
        routeMinimalModel.setDestination( jupiter.getNode());
        routeMinimalModel.setDistance(1.89D);

        Route route = TestDataUtil.mockRoute(2, earth, jupiter, 1.89D);
        when( routeRepository.save( any(Route.class))).thenReturn( route);

        RouteMinimalModel saved = routeService.save(routeMinimalModel);
        assertNotNull( saved);
    }

    @Test
    @SneakyThrows
    void shouldFetchById() {
        Planet earth = TestDataUtil.mockPlanet("A", "Earth");
        Planet jupiter = TestDataUtil.mockPlanet("C", "Jupiter");
        Route earthToJupiter = TestDataUtil.mockRoute(2, earth, jupiter, 1.89D);
        when( routeRepository.findById( earthToJupiter.getId())).thenReturn( Optional.of( earthToJupiter));

        RouteMinimalModel foundRoute = routeService.fetchById(2);
        assertEquals( earthToJupiter.getId(), foundRoute.getId());
        assertEquals( earthToJupiter.getDistance(), foundRoute.getDistance());

        assertEquals( earthToJupiter.getOrigin().getNode(), foundRoute.getOrigin());
        assertEquals( earthToJupiter.getDestination().getNode(), foundRoute.getDestination());
    }

    @Test
    @SneakyThrows
    void shouldDeleteById() {
        Planet earth = TestDataUtil.mockPlanet("A", "Earth");
        Planet jupiter = TestDataUtil.mockPlanet("C", "Jupiter");
        Route earthToJupiter = TestDataUtil.mockRoute(2, earth, jupiter, 1.89D);
        doNothing().when(routeRepository).deleteById( anyInt());

        routeService.deleteById( earthToJupiter.getId());

        ArgumentCaptor<Integer> routeIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        verify (routeRepository, atLeastOnce()).deleteById( routeIdArgCaptor.capture());
        assertEquals( earthToJupiter.getId(), routeIdArgCaptor.getValue());
    }

    @Test
    @SneakyThrows
    void shouldDeleteByPlanetRoutes() {
        Planet earth = TestDataUtil.mockPlanet("A", "Earth");
        Planet jupiter = TestDataUtil.mockPlanet("C", "Jupiter");
        Route earthToJupiter = TestDataUtil.mockRoute(2, earth, jupiter, 1.89D);

        when( routeRepository.findByPlanetOriginAndPlanetDestination( anyString(), anyString()))
                .thenReturn( Lists.newArrayList(earthToJupiter));

        List<Route> routes = routeService.fetchByPlanetOriginAndPlanetDestination( earth.getNode(), jupiter.getNode());
        assertTrue( CollectionUtils.isNotEmpty( routes));
        ArgumentCaptor<String> originNodeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> destinationNodeCaptor = ArgumentCaptor.forClass(String.class);

        verify (routeRepository, atLeastOnce()).findByPlanetOriginAndPlanetDestination( originNodeCaptor.capture(), destinationNodeCaptor.capture());
        assertEquals( earth.getNode(), originNodeCaptor.getValue());
        assertEquals( jupiter.getNode(), destinationNodeCaptor.getValue());
    }
}