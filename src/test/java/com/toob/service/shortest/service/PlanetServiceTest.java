package com.toob.service.shortest.service;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.rest.AbstractServiceTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.toob.service.shortest.util.TestDataUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlanetServiceTest extends AbstractServiceTest {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PlanetQueryService planetQueryService;


    @Test
    void shouldSave() {
        when( planetRepository.save( any(Planet.class))).thenReturn( mockPlanet("A", "Earth"));
        PlanetModel planetModel = TestDataUtil.buildPlanet();
        PlanetModel save = planetService.save(planetModel);
        assertNotNull( save);
    }


    @Test
    void shouldDeleteById() {
        final String venusId = "D";

        ArgumentCaptor<String> deletedNodeArgCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(planetRepository).deleteById( anyString());
        when( planetRepository.findById( anyString())).thenReturn(Optional.empty());

        planetService.deleteById(venusId);
        PlanetModel planetModel = planetQueryService.fetchById(venusId);
        assertNull( planetModel);

        verify (planetRepository, atLeastOnce()).deleteById( deletedNodeArgCapture.capture());
        assertEquals( venusId, deletedNodeArgCapture.getValue());

        verify (planetRepository, atLeastOnce()).findById( deletedNodeArgCapture.capture());
        assertEquals( venusId, deletedNodeArgCapture.getValue());
    }

}