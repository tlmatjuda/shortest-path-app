package za.co.discovery.assignment.thabomatjuda.mapper;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.mapper.RouteMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.model.route.RouteModel;
import com.toob.service.shortest.repository.RouteRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteMapperTest {

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RouteRepository routeRepository;

    @Test
    void shouldMap() {
        Optional<Route> optionalRoute = routeRepository.findById(7);
        assertTrue( optionalRoute.isPresent());

        Route route = optionalRoute.get();
        assertNotNull( route.getRouteId());
        assertNotNull( route.getDistanceInLightYears());

        Planet planetOrigin = route.getPlanetOrigin();
        assertNotNull(planetOrigin);

        Planet planetDestination = route.getPlanetDestination();
        assertNotNull(planetDestination);

        RouteModel routeModel = routeMapper.asModel(route);
        assertNotNull( routeModel);

        assertEquals( route.getRouteId(), routeModel.getRouteId());
        assertEquals( route.getDistanceInLightYears(), routeModel.getDistanceInLightYears());

        PlanetModel originModel = routeModel.getPlanetOrigin();
        PlanetModel destinationModel = routeModel.getPlanetDestination();

        assertEquals( planetOrigin.getPlanetNode(), originModel.getPlanetNode());
        assertEquals( planetOrigin.getPlanetName(), originModel.getPlanetName());

    }


    @Test
    void shouldMapCollection() {
        List<Route> routes = routeRepository.findAll();
        assertTrue( CollectionUtils.isNotEmpty( routes));

        List<RouteModel> modelList = routeMapper.asModel(routes);
        assertTrue( CollectionUtils.isNotEmpty( modelList));
        assertEquals( modelList.size(), routes.size());

        List<Route> entity = routeMapper.asEntity(modelList);
        assertTrue( CollectionUtils.isNotEmpty( entity));
    }
}