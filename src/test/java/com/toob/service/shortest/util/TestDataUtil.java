package com.toob.service.shortest.util;


import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
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

    public static Planet mockPlanet(String node, String name) {
        Planet planet = new Planet();
        planet.setNode(node);
        planet.setName(name);
        return planet;
    }

    public static Route mockRoute(Integer id, Planet origin, Planet destination, Double distance) {
        Route route = new Route();
        route.setId(id);
        route.setOrigin(origin);
        route.setDestination(destination);
        route.setDistance(distance);
        return route;
    }

}
