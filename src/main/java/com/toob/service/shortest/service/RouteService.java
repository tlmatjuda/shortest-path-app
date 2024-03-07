package com.toob.service.shortest.service;

import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.mapper.RouteMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.model.route.RouteModel;
import com.toob.service.shortest.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


/**
 * Business logic class for our Routes domain.
 * @author : Thabo Matjuda
 */
@Slf4j
@Service
@Validated
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    private final PlanetQueryService planetQueryService;

    /**
     * Constructor injection of the required dependencies
     * @param routeRepository
     * @param routeMapper
     * @param planetQueryService
     */
    public RouteService(RouteRepository routeRepository, RouteMapper routeMapper, PlanetQueryService planetQueryService) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
        this.planetQueryService = planetQueryService;
    }

    /**
     * Fetches all the Routes Entries form the database.
     * @return
     */
    public List<RouteModel> fetchAll() {
        List<RouteModel> responseModel = null;
        List<Route> routes = routeRepository.findAll();
        if (CollectionUtils.isNotEmpty( routes)) {
            responseModel = routeMapper.asModel( routes);
        }

        return responseModel;
    }

    /**
     * Saves or Inserts a Route entry into the database.
     * @param routeMinimalModel : Route information to save.
     * @return
     */
    public RouteModel save(@Valid RouteMinimalModel routeMinimalModel) {

        RouteModel routeModel = new RouteModel();
        routeModel.setRouteId(routeMinimalModel.getRouteId());
        routeModel.setDistanceInLightYears(routeMinimalModel.getDistanceInLightYears());

        PlanetModel originPlanet = planetQueryService.fetchById(routeMinimalModel.getPlanetOrigin());
        routeModel.setPlanetOrigin( originPlanet);

        PlanetModel planetModel = planetQueryService.fetchById(routeMinimalModel.getPlanetDestination());
        routeModel.setPlanetDestination( planetModel);

        Route route = routeRepository.save(routeMapper.asEntity(routeModel));
        return routeMapper.asModel( route);
    }

    /**
     * Fetches Route entry by Id.
     * @param routeId : The route Id to search by
     * @return
     */
    public RouteModel fetchById( @NotNull Integer routeId) {
        RouteModel responseModel = null;

        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if ( optionalRoute.isPresent()) {
            responseModel = routeMapper.asModel( optionalRoute.get());
        }

        return responseModel;
    }

    /**
     * Deletes a Route by Id.
     * @param routeId : Used to search and delete.
     */
    public void deleteById(@NotNull Integer routeId) {
        routeRepository.deleteById( routeId);
    }

    /**
     * Searches for a route by Origin Planet and Destinatino Planet.
     * @param originNode : The Origin to look for
     * @param destinationNode : The Destination to look for.
     */
    public void deletePlanetRoutes( @NotBlank String originNode, @NotBlank String destinationNode) {
        routeRepository.deleteRoutesByPlanetOriginAndPlanetDestination( originNode, destinationNode);
    }

    /**
     * Deletes a route by Origin Planet and Destinatino Planet.
     * @param originNode : The Origin to look for when deleting
     * @param destinationNode : The Origin to look for when deleting
     * @return
     */
    public List<Route> fetchByPlanetOriginAndPlanetDestination(@NotBlank String originNode, @NotBlank String destinationNode) {
        return routeRepository.findByPlanetOriginAndPlanetDestination(originNode, destinationNode);
    }
}
