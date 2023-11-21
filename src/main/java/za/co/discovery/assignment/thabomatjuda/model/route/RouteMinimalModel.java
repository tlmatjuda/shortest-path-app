package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RouteMinimalModel extends BaseRouteModel {

    @NotBlank( message = "Origin is required")
    private String planetOrigin;

    @NotBlank( message = "Destination is required")
    private String planetDestination;

}