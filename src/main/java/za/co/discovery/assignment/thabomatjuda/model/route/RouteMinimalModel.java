package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Route Minimal DTO that will carry data to and from the outside world via our REST API
 * @author : Thabo Matjuda
 */
@Getter
@Setter
public class RouteMinimalModel extends BaseRouteModel {

    @NotBlank( message = "Origin is required")
    private String planetOrigin;

    @NotBlank( message = "Destination is required")
    private String planetDestination;

}