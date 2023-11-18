package za.co.discovery.assignment.thabomatjuda.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.mapper.PlanetMapper;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Validated
@Transactional
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;
    private final RouteService routeService;

    public PlanetService( PlanetRepository planetRepository, PlanetMapper planetMapper, RouteService routeService) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
        this.routeService = routeService;
    }

    public List<PlanetModel> fetchAll() {
        List<PlanetModel> responseModel = null;
        List<Planet> planets = planetRepository.findAll();
        if (CollectionUtils.isNotEmpty( planets)) {
            responseModel = planetMapper.asModel( planets);
        }

        return responseModel;
    }

    public PlanetModel save( @Valid PlanetModel planetModel) {
        Planet savedEntity = planetRepository.save(planetMapper.asEntity(planetModel));
        return planetMapper.asModel( savedEntity);
    }

    public PlanetModel fetchById( @NotBlank String planetNode) {
        PlanetModel responseModel = null;

        Optional<Planet> optionalPlanet = planetRepository.findById(planetNode);
        if ( optionalPlanet.isPresent()) {
            responseModel = planetMapper.asModel( optionalPlanet.get());
        }

        return responseModel;
    }

    public void deleteById(@NotBlank String planetNode) {
        routeService.deletePlanetRoutes( planetNode, planetNode);
        planetRepository.deleteById( planetNode);
    }

}
