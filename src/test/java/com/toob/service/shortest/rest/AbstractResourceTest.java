package com.toob.service.shortest.rest;

import com.google.common.collect.Lists;
import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.service.*;
import com.toob.service.shortest.util.TestDataUtil;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@EnableAutoConfiguration( exclude = {
        FlywayAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
public abstract class AbstractResourceTest {

    @MockBean
    protected PlanetService planetService;

    @MockBean
    protected PlanetQueryService planetQueryService;

    @MockBean
    protected RouteService routeService;

    @MockBean
    protected StartupProcesses startupProcesses;

    @MockBean
    protected CalculationService calculationService;

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @MockBean
    protected Flyway flyway;


    static Planet earth;
    static Planet moon;
    static Planet jupiter;
    static Planet venus;

    static ArrayList<Route> mockedRoutes;
    final static Integer EARTH_TO_MOON = 0;
    final static Integer EARTH_TO_JUPITER = 1;
    final static Integer EARTH_TO_VENUS = 2;


    static  {
        earth = TestDataUtil.mockPlanet("A", "Earth");
        moon = TestDataUtil.mockPlanet("B", "Moon");
        jupiter = TestDataUtil.mockPlanet("C", "Jupiter");
        venus = TestDataUtil.mockPlanet("D", "Venus");

        mockedRoutes = Lists.newArrayList(
                TestDataUtil.mockRoute(1, earth, moon, 0.44D),
                TestDataUtil.mockRoute(2, earth, jupiter, 1.89D),
                TestDataUtil.mockRoute(3, earth, venus, 0.10D)
        );
    }




}
