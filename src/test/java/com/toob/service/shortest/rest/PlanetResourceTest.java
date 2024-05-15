package com.toob.service.shortest.rest;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.mapper.PlanetMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.rest.PlanetResource;
import com.toob.service.shortest.service.PlanetQueryService;
import com.toob.service.shortest.service.PlanetService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
class PlanetResourceTest {

    @MockBean
    private PlanetService planetService;

    @MockBean
    private PlanetQueryService planetQueryService;

    @Autowired
    private PlanetResource planetResource;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetMapper planetMapper;

    private static MockHttpServletRequest request;

    @BeforeAll
    static void beforeAll() {
//        request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes( new ServletRequestAttributes(request));
    }

    @Test
    void shouldFetchAll() throws Exception {
        List<PlanetModel> mockedPlanetList = planetMapper.asModel(planetRepository.findAll());
        when(planetQueryService.fetchAll()).thenReturn(mockedPlanetList);

        ResponseEntity<Object> responseEntity = planetResource.fetchAll();
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
    }

    @Test
    void shouldSave() throws Exception {
        Planet planet = planetRepository.findById("A").get();
        PlanetModel planetModel = planetMapper.asModel(planet);

        when(planetService.save( any(PlanetModel.class))).thenReturn(planetModel);

        ResponseEntity<Object> responseEntity = planetResource.save(planetModel);
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
    }

    @Test
    void shouldUpdate() throws Exception {
        final String earthId = "A";
        Planet planet = planetRepository.findById(earthId).get();
        PlanetModel planetModel = planetMapper.asModel(planet);

        when(planetService.save( any(PlanetModel.class))).thenReturn(planetModel);
        when(planetQueryService.fetchById( anyString())).thenReturn(planetModel);

        ResponseEntity<Object> responseEntity = planetResource.update(earthId, planetModel);
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
    }

    @Test
    void shoulDeleteById() throws Exception {
        final String earthId = "A";

        doNothing().when(planetService).deleteById( anyString());

        ResponseEntity<Object> responseEntity = planetResource.deleteById(earthId);
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
    }
}