package com.toob.service.shortest.rest;


import com.toob.service.shortest.constants.RestApiConstants;
import com.toob.service.shortest.model.JsonRouteRequest;
import com.toob.service.shortest.model.JsonRouteResponse;
import com.toob.service.shortest.handler.ShortestPathHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller or Handler for various REST API Requests on te Route Domain
 * @author : Thabo Matjuda
 */
@RestController
@RequestMapping(RestApiConstants.CALCULATION_PATH)
public class CalculationResource {

    private final ShortestPathHandler shortestPathHandler;

    public CalculationResource(ShortestPathHandler shortestPathHandler) {
        this.shortestPathHandler = shortestPathHandler;
    }


    /**
     * Used to SAVE or POST a new Route entry
     * @param routeRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody JsonRouteRequest routeRequest) {
        JsonRouteResponse calculationResult = shortestPathHandler.calculate(routeRequest);
        return new ResponseEntity<>(calculationResult, HttpStatus.OK);
    }



}
