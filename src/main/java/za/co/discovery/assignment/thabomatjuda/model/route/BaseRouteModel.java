package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BaseRouteModel implements Serializable  {

    @NotNull( message = "RouteId is required")
    private Integer routeId;

    @NotNull( message = "Distance is required")
    private Double distanceInLightYears;

}