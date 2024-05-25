package com.toob.service.shortest.rest;


import com.toob.service.shortest.mapper.RouteMapper;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.util.MockedRoutesUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atMostOnce;

@SpringBootTest
class RouteResourceTest extends AbstractResourceTest {

    @Autowired
    private RouteResource routeResource;

    @Autowired
    private RouteMapper routeMapper;

    @Test
    @SneakyThrows
    void shouldFetchAll() {
        when(routeService.fetchAll()).thenReturn(routeMapper.asMinimalModel(MockedRoutesUtil.fetchAll()));

        ResponseEntity<Object> responseEntity = routeResource.fetchAll();
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
        verify(routeService, atMostOnce()).fetchAll();
    }

    @Test
    @SneakyThrows
    void shouldSave() {
        RouteMinimalModel earthToMoon = routeMapper.asMinimalModel( MockedRoutesUtil.fetchById(2));
        when(routeService.save( any(RouteMinimalModel.class))).thenReturn(earthToMoon);

        ResponseEntity<Object> responseEntity = routeResource.save( new RouteMinimalModel());
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
        verify(routeService, atMostOnce()).save(earthToMoon);
    }

    @Test
    @SneakyThrows
    void shouldUpdate() {
        RouteMinimalModel earthToVenus = routeMapper.asMinimalModel(MockedRoutesUtil.fetchById(2));
        when(routeService.fetchById( anyInt())).thenReturn(earthToVenus);
        when(routeService.save( any(RouteMinimalModel.class))).thenReturn(earthToVenus);

        ResponseEntity<Object> responseEntity = routeResource.update(earthToVenus.getId(), new RouteMinimalModel());
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertTrue( responseEntity.hasBody());
        verify(routeService, times(2)).fetchById(earthToVenus.getId());
        verify(routeService, atMostOnce()).save(earthToVenus);
    }

    @Test
    @SneakyThrows
    void shouldDeleteById() {
        RouteMinimalModel earthToVenus = routeMapper.asMinimalModel(MockedRoutesUtil.fetchById(2));
        doNothing().when(routeService).deleteById( anyInt());

        ResponseEntity<Object> responseEntity = routeResource.deleteById(earthToVenus.getId());
        assertNotNull( responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertFalse( responseEntity.hasBody());
        verify(routeService, atMostOnce()).deleteById(earthToVenus.getId());
    }


}