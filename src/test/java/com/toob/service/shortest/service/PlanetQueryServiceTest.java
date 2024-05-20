package com.toob.service.shortest.service;

import com.google.common.collect.Lists;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.util.TestDataUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class PlanetQueryServiceTest extends AbstractServiceTest {

    @Autowired
    private PlanetQueryService planetQueryService;

    Planet earth;
    Planet moon;
    Planet jupiter;
    Planet venus;

    @BeforeEach
    void setUp() {
        earth = TestDataUtil.mockPlanet("A", "Earth");
        moon = TestDataUtil.mockPlanet("B", "Moon");
        jupiter = TestDataUtil.mockPlanet("C", "Jupiter");
        venus = TestDataUtil.mockPlanet("D", "Venus");
    }

    @Test
    @SneakyThrows
    void shouldFetchAll() {
        ArrayList<Planet> planets = Lists.newArrayList(earth, moon, jupiter, venus);
        when( planetRepository.findAll()).thenReturn(planets);

        List<PlanetModel> planetModels = planetQueryService.fetchAll();
        assertTrue( CollectionUtils.isNotEmpty( planetModels));
        verify (planetRepository, atLeastOnce()).findAll();
    }

    @Test
    @SneakyThrows
    void shouldFetchById() {
        when( planetRepository.findById( moon.getNode())).thenReturn( Optional.of(moon));
        PlanetModel foundRecord = planetQueryService.fetchById( moon.getNode());
        assertNotNull( foundRecord);
        assertNotNull( foundRecord.getName());
        assertEquals( moon.getName(), foundRecord.getName());
        verify (planetRepository, atLeastOnce()).findById( moon.getNode());
    }

}