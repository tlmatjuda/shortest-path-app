package com.toob.service.shortest.mapper;

import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.mapper.RouteMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.model.route.RouteModel;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.service.SupportDataFileService;
import com.toob.service.shortest.util.MockedRoutesUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class RouteMapperTest extends AbstractMapperTest {

    @Autowired
    private RouteMapper routeMapper;

    @Test
    void shouldMap() {
        Route route = MockedRoutesUtil.fetchById(1);
        RouteModel routeModel = routeMapper.asModel(route);

        assertEquals( route.getId(), routeModel.getId());
        assertEquals( route.getDistance(), routeModel.getDistance());

        PlanetModel originModel = routeModel.getOrigin();
        PlanetModel destinationModel = routeModel.getDestination();

        assertEquals( route.getOrigin().getNode(), originModel.getNode());
        assertEquals( route.getOrigin().getName(), originModel.getName());
        assertEquals( route.getDestination().getNode(), destinationModel.getNode());
        assertEquals( route.getDestination().getName(), destinationModel.getName());

        RouteMinimalModel minimalModel = routeMapper.asMinimalModel(route);
        assertEquals( route.getId(), minimalModel.getId());
        assertEquals( route.getDistance(), minimalModel.getDistance());
    }


    @Test
    void shouldMapCollection() {
        List<Route> routes = MockedRoutesUtil.fetchAll();
        List<RouteModel> modelList = routeMapper.asModel(routes);

        assertTrue( CollectionUtils.isNotEmpty( modelList));
        assertEquals( modelList.size(), routes.size());

        List<Route> entity = routeMapper.asEntity(modelList);
        assertTrue( CollectionUtils.isNotEmpty( entity));
    }
}