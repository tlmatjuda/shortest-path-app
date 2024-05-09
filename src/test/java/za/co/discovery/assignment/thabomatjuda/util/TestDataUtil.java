package za.co.discovery.assignment.thabomatjuda.util;


import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.model.route.RouteModel;

public class TestDataUtil {


    public static PlanetModel buildPlanet() {
        PlanetModel planetModel = new PlanetModel();
        planetModel.setNode("A");
        planetModel.setName("Earth");

        return planetModel;
    }

    public static RouteModel buildRoute() {
        RouteModel routeModel = new RouteModel();
        routeModel.setId(100);
        routeModel.setDistance(09.0D);

        return routeModel;
    }

}
