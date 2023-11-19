package za.co.discovery.assignment.thabomatjuda.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteMinimalModel;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteModel;
import za.co.discovery.assignment.thabomatjuda.service.RouteService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/routes")
public class RouteResource {

    private final RouteService routeService;

    public RouteResource(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ResponseEntity<Object> fetchAll() {
        List<RouteModel> routeModels = routeService.fetchAll();
        return new ResponseEntity<>(routeModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RouteMinimalModel routeMinimalModel) {
        RouteModel savedRoute = routeService.save(routeMinimalModel);
        return new ResponseEntity<>(savedRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<Object> update(@PathVariable Integer routeId, @RequestBody RouteMinimalModel routeMinimalModel) {
        RouteModel routeModel = routeService.fetchById(routeId);
        if (Objects.nonNull( routeModel)) {
            routeService.save(routeMinimalModel);
        }

        routeModel = routeService.fetchById( routeId);

        return new ResponseEntity<>(routeModel, HttpStatus.OK);
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer routeId) {
        routeService.deleteById(routeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
