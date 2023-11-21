package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RouteModel extends BaseRouteModel  {

    @NotNull( message = "Origin is required")
    private PlanetModel planetOrigin;

    @NotNull( message = "Destination is required")
    private PlanetModel planetDestination;

}