package za.co.discovery.assignment.thabomatjuda.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.mapper.PlanetMapper;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.repository.RouteRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Validated
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public void deletePlanetRoutes( @NotBlank String originNode, @NotBlank String destinationNode) {
        routeRepository.deleteRoutesByPlanetOriginAndPlanetDestination( originNode, destinationNode);
    }

    public List<Route> fetchByPlanetOriginAndPlanetDestination(@NotBlank String originNode, @NotBlank String destinationNode) {
        return routeRepository.findByPlanetOriginAndPlanetDestination(originNode, destinationNode);
    }
}
