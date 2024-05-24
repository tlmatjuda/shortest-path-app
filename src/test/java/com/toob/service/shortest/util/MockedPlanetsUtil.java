package com.toob.service.shortest.util;


import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.model.route.RouteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockedPlanetsUtil {

    private static List<Planet> planets = new ArrayList<>();


    static {
        initMockedPlanets();
    }

    public static List<Planet> fetchAll() {
        return planets;
    }

    public static Planet fetchByNode( String node) {
        Optional<Planet> any = planets.stream()
                .filter(planet -> node.equals(planet.getNode()))
                .findAny();

        assertTrue( any.isPresent(), String.format("Mocked Planet not found : %s", node));

        return any.get();
    }


    private static List<Planet> initMockedPlanets() {
        planets = new ArrayList<>();

        planets.add(new Planet("A", "Earth"));
        planets.add(new Planet("B", "Moon"));
        planets.add(new Planet("C", "Jupiter"));
        planets.add(new Planet("D", "Venus"));
        planets.add(new Planet("E", "Mars"));
        planets.add(new Planet("F", "Saturn"));
        planets.add(new Planet("G", "Uranus"));
        planets.add(new Planet("H", "Pluto"));
        planets.add(new Planet("I", "Neptune"));
        planets.add(new Planet("J", "Mercury"));
        planets.add(new Planet("K", "Alpha Centauri"));
        planets.add(new Planet("L", "Luhman 16"));
        planets.add(new Planet("M", "Epsilon Eridani"));
        planets.add(new Planet("N", "Groombridge 34"));
        planets.add(new Planet("O", "Epsilon Indi"));
        planets.add(new Planet("P", "Tau Ceti"));
        planets.add(new Planet("Q", "Kapteyn's star"));
        planets.add(new Planet("R", "Gliese 687"));
        planets.add(new Planet("S", "Gliese 674"));
        planets.add(new Planet("T", "Gliese 876"));
        planets.add(new Planet("U", "Gliese 832"));
        planets.add(new Planet("V", "Fomalhaut"));
        planets.add(new Planet("W", "Virginis"));
        planets.add(new Planet("X", "HD 102365"));
        planets.add(new Planet("Y", "Gliese 176"));
        planets.add(new Planet("Z", "Gliese 436"));
        planets.add(new Planet("A'", "Pollux"));
        planets.add(new Planet("B'", "Gliese 86"));
        planets.add(new Planet("C'", "HIP 57050"));
        planets.add(new Planet("D'", "Piscium"));
        planets.add(new Planet("E'", "GJ 1214"));
        planets.add(new Planet("F'", "Upsilon Andromedae"));
        planets.add(new Planet("G'", "Gamma Cephei"));
        planets.add(new Planet("H'", "HD 176051"));
        planets.add(new Planet("I'", "Gliese 317"));
        planets.add(new Planet("J'", "HD 38858"));
        planets.add(new Planet("K'", "Ursae Majoris"));

        return planets;
    }
}
