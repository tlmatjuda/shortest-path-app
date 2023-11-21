package za.co.discovery.assignment.thabomatjuda.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.thabomatjuda.constants.RestApiConstants;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteMinimalModel;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteModel;
import za.co.discovery.assignment.thabomatjuda.service.RouteService;

import java.util.List;
import java.util.Objects;


/**
 * A Controller or Handler for various REST API Requests on te Route Domain
 * @author : Thabo Matjuda
 */
@RestController
@RequestMapping(RestApiConstants.ROUTEST_PATH)
public class RouteResource {

    private final RouteService routeService;

    /**
     * Constructor Injection of required dependencies
     * @param routeService
     */
    public RouteResource(RouteService routeService) {
        this.routeService = routeService;
    }

    /**
     * Used to FETCH-ALL the Routes we have stored.
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> fetchAll() {
        List<RouteModel> routeModels = routeService.fetchAll();
        return new ResponseEntity<>(routeModels, HttpStatus.OK);
    }

    /**
     * Used to SAVE or POST a new Route entry
     * @param routeMinimalModel
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RouteMinimalModel routeMinimalModel) {
        RouteModel savedRoute = routeService.save(routeMinimalModel);
        return new ResponseEntity<>(savedRoute, HttpStatus.CREATED);
    }

    /**
     * UPDATES or PUTS an existing Route Entry.
     * @param routeId : The Id of the Route to update
     * @param routeMinimalModel : Accepts this Route structure as a Body.
     * @return
     */
    @PutMapping("/{routeId}")
    public ResponseEntity<Object> update(@PathVariable Integer routeId, @RequestBody RouteMinimalModel routeMinimalModel) {
        RouteModel routeModel = routeService.fetchById(routeId);
        if (Objects.nonNull( routeModel)) {
            routeService.save(routeMinimalModel);
        }

        routeModel = routeService.fetchById( routeId);

        return new ResponseEntity<>(routeModel, HttpStatus.OK);
    }


    /**
     * DELETES to REMOVES a Route Entry by Id.
     * @param routeId
     * @return
     */
    @DeleteMapping("/{routeId}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer routeId) {
        routeService.deleteById(routeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
