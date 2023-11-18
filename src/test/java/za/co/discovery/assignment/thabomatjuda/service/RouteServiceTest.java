package za.co.discovery.assignment.thabomatjuda.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteMinimalModel;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteModel;
import za.co.discovery.assignment.thabomatjuda.util.TestDataUtil;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PlanetService planetService;


    @Test
    void shouldFetchAll() throws Exception {
        List<RouteModel> routeModels = routeService.fetchAll();
        assertTrue( CollectionUtils.isNotEmpty( routeModels));
    }

    @Test
    void shouldSave() throws Exception {
        RouteMinimalModel routeMinimalModel = new RouteMinimalModel();
        routeMinimalModel.setRouteId(240);
        routeMinimalModel.setDistanceInLightYears(40.0D);
        routeMinimalModel.setPlanetOrigin("D");
        routeMinimalModel.setPlanetDestination("L");

        RouteModel savedRoute = routeService.save(routeMinimalModel);
        assertNotNull( savedRoute);
    }

    @Test
    void shouldFetchById() {
        RouteModel routeModel = routeService.fetchById(7);
        assertNotNull( routeModel);
        assertEquals( 7, routeModel.getRouteId());
        assertEquals( 2.34D, routeModel.getDistanceInLightYears());

        assertNotNull( routeModel.getPlanetOrigin());
        assertEquals( "D", routeModel.getPlanetOrigin().getPlanetNode());

        assertNotNull( routeModel.getPlanetDestination());
        assertEquals( "L", routeModel.getPlanetDestination().getPlanetNode());
    }

    @Test
    void shouldDeleteById() {
        final int routeIdToRemove = 10;
        RouteModel routeModel = routeService.fetchById(routeIdToRemove);
        assertNotNull( routeModel);

        routeService.deleteById( routeIdToRemove);
        assertNull( routeService.fetchById(routeIdToRemove));
    }

    @Test
    @Transactional
    void shouldDeleteByPlanetRoutes() {
        final String sourcePlanetNode = "R";
        final String destinationPlanetNode = "P";
        List<Route> routes = routeService.fetchByPlanetOriginAndPlanetDestination(sourcePlanetNode, destinationPlanetNode);
        assertTrue( CollectionUtils.isNotEmpty( routes));

        routeService.deletePlanetRoutes( sourcePlanetNode, destinationPlanetNode);
        routes = routeService.fetchByPlanetOriginAndPlanetDestination(sourcePlanetNode, destinationPlanetNode);
        assertTrue( CollectionUtils.isEmpty( routes));
    }
}