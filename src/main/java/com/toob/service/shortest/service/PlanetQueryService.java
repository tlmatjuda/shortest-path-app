package com.toob.service.shortest.service;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.mapper.PlanetMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;



/**
 * A Query Service for information about the Planet domain.
 * @author : Thabo Matjuda
 */
@Slf4j
@Service
@Validated
public class PlanetQueryService {

    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;

    /**
     * Constructore injection of our required dependencies
     * @param planetRepository
     * @param planetMapper
     */
    public PlanetQueryService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    /**
     * Queries the database for all Planets records
     * @return : A list of the Planets found in the database
     */
    public List<PlanetModel> fetchAll() {
        List<PlanetModel> responseModel = null;
        List<Planet> planets = planetRepository.findAll();
        if (CollectionUtils.isNotEmpty( planets)) {
            responseModel = planetMapper.asModel( planets);
        }

        return responseModel;
    }

    /**
     * Finds a single Planet record from the database.
     * @param planetNode : Used to search for the data.
     * @return
     */
    public PlanetModel fetchById( @NotBlank String planetNode) {
        PlanetModel responseModel = null;

        Optional<Planet> optionalPlanet = planetRepository.findById(planetNode);
        if ( optionalPlanet.isPresent()) {
            responseModel = planetMapper.asModel( optionalPlanet.get());
        }

        return responseModel;
    }

}
