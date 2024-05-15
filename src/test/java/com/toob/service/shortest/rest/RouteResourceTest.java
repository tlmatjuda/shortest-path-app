package com.toob.service.shortest.rest;


import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.mapper.RouteMapper;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.model.route.RouteModel;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.rest.RouteResource;
import com.toob.service.shortest.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class RouteResourceTest {

    @MockBean
    private RouteService routeService;

    @Autowired
    private RouteResource routeResource;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteMapper routeMapper;

    @Test
    void shouldFetchAll() throws Exception {
        List<RouteModel> mockedRoutes = routeMapper.asModel(routeRepository.findAll());
        when(routeService.fetchAll()).thenReturn(mockedRoutes);

        ResponseEntity<Object> responseEntity = routeResource.fetchAll();
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
    }

    @Test
    void shouldSave() throws Exception {
        Route route = routeRepository.findById(7).get();
        RouteModel routeModel = routeMapper.asModel(route);

        when(routeService.save( any(RouteMinimalModel.class))).thenReturn(routeModel);

        ResponseEntity<Object> responseEntity = routeResource.save( new RouteMinimalModel());
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
    }

    @Test
    void shouldUpdate() throws Exception {
        final int routeEight = 8;
        Route route = routeRepository.findById(routeEight).get();
        RouteModel routeModel = routeMapper.asModel(route);

        when(routeService.save( any(RouteMinimalModel.class))).thenReturn(routeModel);
        when(routeService.fetchById( anyInt())).thenReturn(routeModel);

        ResponseEntity<Object> responseEntity = routeResource.update(routeEight, new RouteMinimalModel());
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
    }

    @Test
    void shoulDeleteById() throws Exception {
        final int earthId = 9;

        doNothing().when(routeService).deleteById( anyInt());

        ResponseEntity<Object> responseEntity = routeResource.deleteById(earthId);
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
    }


}