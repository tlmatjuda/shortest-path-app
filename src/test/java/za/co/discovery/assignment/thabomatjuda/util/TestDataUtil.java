package za.co.discovery.assignment.thabomatjuda.util;


import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.model.route.RouteModel;

public class TestDataUtil {


    public static PlanetModel buildPlanet() {
        PlanetModel planetModel = new PlanetModel();
        planetModel.setPlanetNode("A");
        planetModel.setPlanetName("Earth");

        return planetModel;
    }

    public static RouteModel buildRoute() {
        RouteModel routeModel = new RouteModel();
        routeModel.setRouteId(100);
        routeModel.setDistanceInLightYears(09.0D);

        return routeModel;
    }

}
