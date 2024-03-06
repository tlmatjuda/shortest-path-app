package za.co.discovery.assignment.thabomatjuda.service;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.service.SupportDataFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import za.co.discovery.assignment.thabomatjuda.config.BaseTestConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringBootTest
@Import(BaseTestConfiguration.class)
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
    }


}