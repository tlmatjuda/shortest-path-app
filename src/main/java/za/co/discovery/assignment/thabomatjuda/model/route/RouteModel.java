package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Data;
import za.co.discovery.assignment.thabomatjuda.model.PlanetModel;

import javax.validation.constraints.NotNull;

@Data
public class RouteModel extends BaseRouteModel  {

    @NotNull( message = "Origin is required")
    private PlanetModel planetOrigin;

    @NotNull( message = "Destination is required")
    private PlanetModel planetDestination;

}