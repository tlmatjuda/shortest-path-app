package za.co.discovery.assignment.thabomatjuda.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.mapper.PlanetMapper;
import za.co.discovery.assignment.thabomatjuda.mapper.RouteMapper;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteMinimalModel;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteModel;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.repository.RouteRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Validated
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final PlanetRepository planetRepository;

    private final PlanetMapper planetMapper;

    public RouteService(RouteRepository routeRepository, RouteMapper routeMapper, PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    public List<RouteModel> fetchAll() {
        List<RouteModel> responseModel = null;
        List<Route> routes = routeRepository.findAll();
        if (CollectionUtils.isNotEmpty( routes)) {
            responseModel = routeMapper.asModel( routes);
        }

        return responseModel;
    }

    public RouteModel save(@Valid RouteMinimalModel routeMinimalModel) {

        RouteModel routeModel = new RouteModel();
        routeModel.setRouteId(routeMinimalModel.getRouteId());
        routeModel.setDistanceInLightYears(routeMinimalModel.getDistanceInLightYears());

        Optional<Planet> optionalOrigin = planetRepository.findById(routeMinimalModel.getPlanetOrigin());
        optionalOrigin.ifPresent( planet -> routeModel.setPlanetOrigin(planetMapper.asModel(planet)));

        Optional<Planet> optionalDestination = planetRepository.findById(routeMinimalModel.getPlanetDestination());
        optionalDestination.ifPresent( planet -> routeModel.setPlanetDestination(planetMapper.asModel(planet)));

        Route route = routeRepository.save(routeMapper.asEntity(routeModel));
        return routeMapper.asModel( route);
    }

    public RouteModel fetchById( @NotNull Integer routeId) {
        RouteModel responseModel = null;

        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if ( optionalRoute.isPresent()) {
            responseModel = routeMapper.asModel( optionalRoute.get());
        }

        return responseModel;
    }

    public void deleteById(@NotNull Integer routeId) {
        routeRepository.deleteById( routeId);
    }

    public void deletePlanetRoutes( @NotBlank String originNode, @NotBlank String destinationNode) {
        routeRepository.deleteRoutesByPlanetOriginAndPlanetDestination( originNode, destinationNode);
    }

    public List<Route> fetchByPlanetOriginAndPlanetDestination(@NotBlank String originNode, @NotBlank String destinationNode) {
        return routeRepository.findByPlanetOriginAndPlanetDestination(originNode, destinationNode);
    }
}
