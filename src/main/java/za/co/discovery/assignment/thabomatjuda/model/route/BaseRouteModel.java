package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Base Route DTO that will carry data to and from the outside world via our REST API
 * This has the common information that we may need for either the route detailed or minimal data
 * @author : Thabo Matjuda
 */
@Getter
@Setter
@MappedSuperclass
public class BaseRouteModel implements Serializable  {

    @NotNull( message = "RouteId is required")
    private Integer routeId;

    @NotNull( message = "Distance is required")
    private Double distanceInLightYears;

}