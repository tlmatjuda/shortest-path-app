package com.toob.service.shortest.model.route;

import com.toob.service.shortest.model.planet.PlanetModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


/**
 * Route DTO that will carry data to and from the outside world via our REST API
 * @author : Thabo Matjuda
 */
@Getter
@Setter
public class RouteModel extends BaseRouteModel {

    @NotNull( message = "Origin is required")
    private PlanetModel planetOrigin;

    @NotNull( message = "Destination is required")
    private PlanetModel planetDestination;

}