package com.toob.service.shortest.util;


import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockedRoutesUtil {

    private static List<Route> routes = new ArrayList<>();


    static {
        initMockedRoutes();
    }

    public static List<Route> fetchAll() {
        return routes;
    }

    public static Route fetchById( Integer id) {
        Optional<Route> any = routes.stream()
                .filter(route -> Objects.equals(id, route.getId()))
                .findAny();

        assertTrue( any.isPresent(), String.format("Mocked Route not found : %s", id));

        return any.get();
    }


    private static List<Route> initMockedRoutes() {
        routes = new ArrayList<>();

        routes.add(new Route(1, new Planet("A", "Earth"), new Planet("B", "Moon"), 0.44));
        routes.add(new Route(2, new Planet("A", "Earth"), new Planet("C", "Jupiter"), 1.89));
        routes.add(new Route(3, new Planet("A", "Earth"), new Planet("D", "Venus"), 0.10));
        routes.add(new Route(4, new Planet("B", "Moon"), new Planet("H", "Pluto"), 2.44));
        routes.add(new Route(5, new Planet("B", "Moon"), new Planet("E", "Mars"), 3.45));
        routes.add(new Route(6, new Planet("C", "Jupiter"), new Planet("F", "Saturn"), 0.49));
        routes.add(new Route(7, new Planet("D", "Venus"), new Planet("L", "Luhman 16"), 2.34));
        routes.add(new Route(8, new Planet("D", "Venus"), new Planet("M", "Epsilon Eridani"), 2.61));
        routes.add(new Route(9, new Planet("H", "Pluto"), new Planet("G", "Uranus"), 3.71));
        routes.add(new Route(10, new Planet("E", "Mars"), new Planet("I", "Neptune"), 8.09));
        routes.add(new Route(11, new Planet("F", "Saturn"), new Planet("J", "Mercury"), 5.46));
        routes.add(new Route(12, new Planet("F", "Saturn"), new Planet("K", "Alpha Centauri"), 3.67));
        routes.add(new Route(13, new Planet("G", "Uranus"), new Planet("Z", "Gliese 436"), 5.25));
        routes.add(new Route(14, new Planet("I", "Neptune"), new Planet("Z", "Gliese 436"), 13.97));
        routes.add(new Route(15, new Planet("I", "Neptune"), new Planet("J", "Mercury"), 14.78));
        routes.add(new Route(16, new Planet("L", "Luhman 16"), new Planet("T", "Gliese 876"), 15.23));
        routes.add(new Route(17, new Planet("T", "Gliese 876"), new Planet("N", "Groombridge 34"), 10.43));
        routes.add(new Route(18, new Planet("T", "Gliese 876"), new Planet("S", "Gliese 674"), 14.22));
        routes.add(new Route(19, new Planet("S", "Gliese 674"), new Planet("O", "Epsilon Indi"), 6.02));
        routes.add(new Route(20, new Planet("O", "Epsilon Indi"), new Planet("U", "Gliese 832"), 5.26));
        routes.add(new Route(21, new Planet("J", "Mercury"), new Planet("R", "Gliese 687"), 12.34));
        routes.add(new Route(22, new Planet("R", "Gliese 687"), new Planet("P", "Tau Ceti"), 10.10));
//        routes.add(new Route(23, new Planet("R", "Gliese 687"), new Planet("L'", "Luhman 16"), 9.95));
        routes.add(new Route(24, new Planet("Z", "Gliese 436"), new Planet("Y", "Gliese 176"), 18.91));
        routes.add(new Route(25, new Planet("Y", "Gliese 176"), new Planet("Q", "Kapteyn's star"), 22.04));
        routes.add(new Route(26, new Planet("Q", "Kapteyn's star"), new Planet("X", "HD 102365"), 10.51));
//        routes.add(new Route(27, new Planet("L'", "Luhman 16"), new Planet("X", "HD 102365"), 23.61));
        routes.add(new Route(28, new Planet("X", "HD 102365"), new Planet("K'", "Alpha Centauri"), 19.94));
        routes.add(new Route(29, new Planet("P", "Venus"), new Planet("U", "Gliese 832"), 9.31));
        routes.add(new Route(30, new Planet("U", "Gliese 832"), new Planet("K'", "Alpha Centauri"), 21.23));
        routes.add(new Route(31, new Planet("U", "Gliese 832"), new Planet("J'", "Mercury"), 25.96));
        routes.add(new Route(32, new Planet("J'", "Mercury"), new Planet("V", "Fomalhaut"), 3.16));
        routes.add(new Route(33, new Planet("K'", "Alpha Centauri"), new Planet("V", "Fomalhaut"), 20.42));
        routes.add(new Route(34, new Planet("J'", "Mercury"), new Planet("I'", "Neptune"), 17.10));
        routes.add(new Route(35, new Planet("Y", "Gliese 176"), new Planet("A'", "Pollux"), 19.52));
        routes.add(new Route(36, new Planet("A'", "Pollux"), new Planet("B'", "Gliese 86"), 31.56));
        routes.add(new Route(37, new Planet("B'", "Gliese 86"), new Planet("C'", "HIP 57050"), 23.13));
        routes.add(new Route(38, new Planet("K'", "Alpha Centauri"), new Planet("W", "Virginis"), 28.89));
        routes.add(new Route(39, new Planet("W", "Virginis"), new Planet("C'", "HIP 57050"), 10.64));
        routes.add(new Route(40, new Planet("W", "Virginis"), new Planet("E'", "GJ 1214"), 36.19));
        routes.add(new Route(41, new Planet("C'", "HIP 57050"), new Planet("D'", "Piscium"), 36.48));
        routes.add(new Route(42, new Planet("E'", "GJ 1214"), new Planet("E'", "GJ 1214"), 41.26));
        routes.add(new Route(43, new Planet("E'", "GJ 1214"), new Planet("F'", "Upsilon Andromedae"), 42.07));
        routes.add(new Route(44, new Planet("F'", "Upsilon Andromedae"), new Planet("G'", "Gamma Cephei"), 17.63));
        routes.add(new Route(45, new Planet("G'", "Gamma Cephei"), new Planet("H'", "HD 176051"), 40.48));

        return routes;
    }
}
