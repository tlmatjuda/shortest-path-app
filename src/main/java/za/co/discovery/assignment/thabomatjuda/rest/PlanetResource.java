package za.co.discovery.assignment.thabomatjuda.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.service.PlanetService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

    private final PlanetService planetService;

    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping
    public ResponseEntity<Object> fetchAll() {
        List<PlanetModel> planetModels = planetService.fetchAll();
        return new ResponseEntity<>(planetModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PlanetModel planetModel) {
        planetService.save(planetModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{planetNode}")
    public ResponseEntity<Object> update(@PathVariable String planetNode, @RequestBody PlanetModel planetModel) {
        PlanetModel existingPlanet = planetService.fetchById(planetNode);
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
