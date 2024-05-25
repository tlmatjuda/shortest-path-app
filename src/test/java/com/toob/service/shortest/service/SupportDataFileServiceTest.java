package com.toob.service.shortest.service;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import com.toob.service.shortest.TestConfigFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;


@Slf4j
class SupportDataFileServiceTest extends AbstractServiceTest {

    @Captor
    private ArgumentCaptor<List<Planet>> planetsCaptor;

    @Captor
    private ArgumentCaptor<List<Route>> routesCaptor;

    @Autowired
    private SupportDataFileService supportDataFileService;


    @Test
    void shouldProcessData() throws Exception {
        supportDataFileService.process();

        verify(planetRepository).saveAll(planetsCaptor.capture());
        verify(routeRepository).saveAll(routesCaptor.capture());

        List<Planet> savedPlanets = planetsCaptor.getValue();
        assertTrue(CollectionUtils.isNotEmpty( savedPlanets));

        List<Route> savedRoutes = routesCaptor.getValue();
        assertTrue(CollectionUtils.isNotEmpty( savedRoutes));
    }


}