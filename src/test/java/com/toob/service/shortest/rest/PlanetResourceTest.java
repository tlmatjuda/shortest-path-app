package com.toob.service.shortest.rest;

import com.google.common.collect.Lists;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.mapper.PlanetMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atMostOnce;


@SpringBootTest
class PlanetResourceTest extends AbstractResourceTest {

    @Autowired
    private PlanetResource planetResource;

    @Autowired
    private PlanetMapper planetMapper;

    private static MockHttpServletRequest request;

    static Planet earth;
    static Planet moon;
    static Planet jupiter;
    static Planet venus;

    @Test
    @SneakyThrows
    void shouldFetchAll() {
        List<PlanetModel> mockedPlanetList = planetMapper.asModel( Lists.newArrayList(earth, moon, jupiter, venus) );
        when(planetQueryService.fetchAll()).thenReturn(mockedPlanetList);

        ResponseEntity<Object> responseEntity = planetResource.fetchAll();
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
        verify(planetQueryService, atMostOnce()).fetchAll();
    }

    @Test
    @SneakyThrows
    void shouldSave() {
        PlanetModel jupiterModel = planetMapper.asModel(jupiter);
        when(planetService.save( any(PlanetModel.class))).thenReturn(jupiterModel);

        ResponseEntity<Object> responseEntity = planetResource.save(jupiterModel);
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
    }

    @Test
    @SneakyThrows
    void shouldUpdate() {
        PlanetModel earthModel = planetMapper.asModel(earth);

        when(planetQueryService.fetchById( anyString())).thenReturn(earthModel);
        when(planetService.save( any(PlanetModel.class))).thenReturn(earthModel);

        ResponseEntity<Object> responseEntity = planetResource.update(earthModel.getNode(), earthModel);
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());

        verify(planetQueryService, atMostOnce()).fetchById( earthModel.getNode());
        verify(planetService, atMostOnce()).save( earthModel);
    }

    @Test
    @SneakyThrows
    void shoulDeleteById() {
        doNothing().when(planetService).deleteById( anyString());

        ResponseEntity<Object> responseEntity = planetResource.deleteById(earth.getNode());
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
        verify(planetService, atMostOnce()).deleteById( earth.getNode());
    }
}