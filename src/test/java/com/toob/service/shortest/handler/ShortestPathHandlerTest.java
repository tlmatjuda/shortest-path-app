package com.toob.service.shortest.handler;

import com.toob.service.shortest.AbstractSpringIntegrationTest;
import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.model.JsonRouteRequest;
import com.toob.service.shortest.model.JsonRouteResponse;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.util.MockedPlanetsUtil;
import com.toob.service.shortest.util.MockedRoutesUtil;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Slf4j
class ShortestPathHandlerTest extends AbstractSpringIntegrationTest {

    @MockBean
    protected PlanetRepository planetRepository;

    @MockBean
    protected RouteRepository routeRepository;

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private ShortestPathHandler shortestPathHandler;

    @MockBean
    protected Flyway flyway;

    @BeforeEach
    void setUp() {
        when(planetRepository.findAll()).thenReturn(MockedPlanetsUtil.fetchAll());
        when(routeRepository.findAll()).thenReturn(MockedRoutesUtil.fetchAll());

        calculatorService.initialise();
    }

    @Test
    void shouldCalculate() {

        JsonRouteRequest routeRequest = new JsonRouteRequest();
        routeRequest.setOrigin("A");
        routeRequest.setDestination("F");

        when(planetRepository.findById(eq("A"))).thenReturn(Optional.of(MockedPlanetsUtil.fetchByNode("A")));
        when(planetRepository.findById(eq("C"))).thenReturn(Optional.of(MockedPlanetsUtil.fetchByNode("C")));
        when(planetRepository.findById(eq("F"))).thenReturn(Optional.of(MockedPlanetsUtil.fetchByNode("F")));

        JsonRouteResponse routeResponse = shortestPathHandler.calculate(routeRequest);
        assertNotNull(routeResponse);
    }

}