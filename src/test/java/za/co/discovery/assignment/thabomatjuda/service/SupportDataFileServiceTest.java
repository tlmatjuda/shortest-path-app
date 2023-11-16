package za.co.discovery.assignment.thabomatjuda.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import za.co.discovery.assignment.thabomatjuda.config.BestTestConfiguration;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.repository.RouteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringBootTest
@Import(BestTestConfiguration.class)
class SupportDataFileServiceTest {


    @Autowired
    private SupportDataFileService supportDataFileService;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;


    @Test
    void shouldProcessData() throws Exception {
        supportDataFileService.process();

        List<Planet> planets = planetRepository.findAll();
        assertTrue( CollectionUtils.isNotEmpty( planets));
        assertEquals( 37, planets.size());


        List<Route> routes = routeRepository.findAll();
        assertTrue( CollectionUtils.isNotEmpty( routes));
        assertEquals( 45, routes.size());
    }


}