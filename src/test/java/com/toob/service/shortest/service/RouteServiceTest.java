package com.toob.service.shortest.service;

import com.google.common.collect.Lists;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.util.MockedPlanetsUtil;
import com.toob.service.shortest.util.MockedRoutesUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

class RouteServiceTest extends AbstractServiceTest {

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private PlanetService planetService;


    @Test
    @SneakyThrows
    void shouldFetchAll() {
        ArrayList<Route> mockedRoutes = Lists.newArrayList(
                MockedRoutesUtil.fetchById(1),
                MockedRoutesUtil.fetchById(2),
                MockedRoutesUtil.fetchById(3)
        );

        when( routeRepository.findAll()).thenReturn(mockedRoutes);

        List<RouteMinimalModel> routeModels = routeService.fetchAll();
        assertTrue( CollectionUtils.isNotEmpty( routeModels));
    }

    @Test
    @SneakyThrows
    void shouldSave() {
        Planet earth = MockedPlanetsUtil.fetchByNode("A");
        Planet jupiter = MockedPlanetsUtil.fetchByNode("C");

        when( planetRepository.findById( earth.getNode())).thenReturn( Optional.of( earth));
        when( planetRepository.findById( jupiter.getNode())).thenReturn( Optional.of( jupiter));

        RouteMinimalModel routeMinimalModel = new RouteMinimalModel();
        routeMinimalModel.setId(2);
        routeMinimalModel.setOrigin( earth.getNode());
        routeMinimalModel.setDestination( jupiter.getNode());
        routeMinimalModel.setDistance(1.89D);

        Route route = MockedRoutesUtil.fetchById(2);
        when( routeRepository.save( any(Route.class))).thenReturn( route);

        RouteMinimalModel saved = routeService.save(routeMinimalModel);
        assertNotNull( saved);
    }

    @Test
    @SneakyThrows
    void shouldFetchById() {
//        Planet earth = MockedPlanetsUtil.fetchByNode("A");
//        Planet jupiter = MockedPlanetsUtil.fetchByNode("C");
        Route earthToJupiter = MockedRoutesUtil.fetchById(2);
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
//        Planet earth = MockedPlanetsUtil.fetchByNode("A");
//        Planet jupiter = MockedPlanetsUtil.fetchByNode("C");
        Route earthToJupiter = MockedRoutesUtil.fetchById(2);
        doNothing().when(routeRepository).deleteById( anyInt());

        routeService.deleteById( earthToJupiter.getId());

        ArgumentCaptor<Integer> routeIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        verify (routeRepository, atLeastOnce()).deleteById( routeIdArgCaptor.capture());
        assertEquals( earthToJupiter.getId(), routeIdArgCaptor.getValue());
    }

    @Test
    @SneakyThrows
    void shouldDeleteByPlanetRoutes() {
        Planet earth = MockedPlanetsUtil.fetchByNode("A");
        Planet jupiter = MockedPlanetsUtil.fetchByNode("A");
        Route earthToJupiter = MockedRoutesUtil.fetchById(2);

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