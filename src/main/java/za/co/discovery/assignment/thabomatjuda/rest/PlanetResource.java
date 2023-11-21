package za.co.discovery.assignment.thabomatjuda.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.thabomatjuda.constants.RestApiConstants;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.service.PlanetQueryService;
import za.co.discovery.assignment.thabomatjuda.service.PlanetService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(RestApiConstants.PLANETS_PATH)
public class PlanetResource {

    private final PlanetService planetService;

    private final PlanetQueryService planetQueryService;

    public PlanetResource(PlanetService planetService, PlanetQueryService planetQueryService) {
        this.planetService = planetService;
        this.planetQueryService = planetQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> fetchAll() {
        List<PlanetModel> planetModels = planetQueryService.fetchAll();
        return new ResponseEntity<>(planetModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PlanetModel planetModel) {
        planetService.save(planetModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{planetNode}")
    public ResponseEntity<Object> update(@PathVariable String planetNode, @RequestBody PlanetModel planetModel) {
        PlanetModel existingPlanet = planetQueryService.fetchById(planetNode);
        if (Objects.nonNull( existingPlanet)){
            planetService.save(planetModel);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{planetNode}")
    public ResponseEntity<Object> deleteById(@PathVariable String planetNode) {
        planetService.deleteById(planetNode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
