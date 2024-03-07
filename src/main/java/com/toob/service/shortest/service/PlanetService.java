package com.toob.service.shortest.service;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.mapper.PlanetMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.repository.PlanetRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


/**
 * Business logic class for the Planets Domain.
 * @author : Thabo Matjuda
 */
@Slf4j
@Service
@Validated
@Transactional
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;
    private final RouteService routeService;

    /**
     * Constructor injection for required dependencies.
     * @param planetRepository
     * @param planetMapper
     * @param routeService
     */
    public PlanetService( PlanetRepository planetRepository, PlanetMapper planetMapper, RouteService routeService) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
        this.routeService = routeService;
    }

    /**
     * Insets or Updates a Planet based on the given Planet info.
     * @param planetModel : The planet data to save.
     * @return
     */
    public PlanetModel save(@Valid PlanetModel planetModel) {
        Planet savedEntity = planetRepository.save(planetMapper.asEntity(planetModel));
        return planetMapper.asModel( savedEntity);
    }

    /**
     * Deletes a Planet record by the given Node (ID)
     * @param planetNode : The Node to remove by
     */
    public void deleteById(@NotBlank String planetNode) {
        routeService.deletePlanetRoutes( planetNode, planetNode);
        planetRepository.deleteById( planetNode);
    }

}
