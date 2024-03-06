package com.toob.service.shortest.rest;


import com.toob.service.shortest.constants.RestApiConstants;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.service.PlanetQueryService;
import com.toob.service.shortest.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * A Controller or Handler for various REST API Requests on te Planets Domain
 * @author : Thabo Matjuda
 */
@RestController
@RequestMapping(RestApiConstants.PLANETS_PATH)
public class PlanetResource {

    private final PlanetService planetService;
    private final PlanetQueryService planetQueryService;

    /**
     * Constructor Injection of required dependencies
     * @param planetService
     * @param planetQueryService
     */
    public PlanetResource(PlanetService planetService, PlanetQueryService planetQueryService) {
        this.planetService = planetService;
        this.planetQueryService = planetQueryService;
    }

    /**
     * Used to FETCH-ALL the Planets we have stored.
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> fetchAll() {
        List<PlanetModel> planetModels = planetQueryService.fetchAll();
        return new ResponseEntity<>(planetModels, HttpStatus.OK);
    }

    /**
     * Used to SAVE or POST a new Planet entry
     * @param planetModel : Takes in this structure as the Body.
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PlanetModel planetModel) {
        planetService.save(planetModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     *
     * @param planetNode : Takes in this parameter as an ID for the entry.
     * @param planetModel : Accepts this Planet structure as a Body.
     * @return
     */
    @PutMapping("/{planetNode}")
    public ResponseEntity<Object> update(@PathVariable String planetNode, @RequestBody PlanetModel planetModel) {
        PlanetModel existingPlanet = planetQueryService.fetchById(planetNode);
        if (Objects.nonNull( existingPlanet)){
            planetService.save(planetModel);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * DELETES to REMOVES a Planet Entry by Id.
     * @param planetNode : The given Id to remove.
     * @return
     */
    @DeleteMapping("/{planetNode}")
    public ResponseEntity<Object> deleteById(@PathVariable String planetNode) {
        planetService.deleteById(planetNode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
