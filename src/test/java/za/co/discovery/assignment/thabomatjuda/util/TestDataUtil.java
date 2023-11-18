package za.co.discovery.assignment.thabomatjuda.util;

import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;

public class TestDataUtil {


    public static PlanetModel buildPlanet() {
        PlanetModel planetModel = new PlanetModel();
        planetModel.setPlanetNode("A");
        planetModel.setPlanetName("Earth");

        return planetModel;
    }

}
