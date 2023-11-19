package za.co.discovery.assignment.thabomatjuda.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.mapper.PlanetMapper;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.service.PlanetService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
class PlanetResourceTest {

    @MockBean
    private PlanetService planetService;

    @Autowired
    private  PlanetResource planetResource;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetMapper planetMapper;

    private static MockHttpServletRequest request;

    @BeforeAll
    static void beforeAll() {
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes( new ServletRequestAttributes(request));
    }

    @Test
    void shouldFetchAll() throws Exception {
        List<PlanetModel> mockedPlanetList = planetMapper.asModel(planetRepository.findAll());
        when(planetService.fetchAll()).thenReturn(mockedPlanetList);

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
        when(planetService.fetchById( anyString())).thenReturn(planetModel);

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