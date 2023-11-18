package za.co.discovery.assignment.thabomatjuda.model.route;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RouteMinimalModel extends BaseRouteModel {

    @NotBlank( message = "Origin is required")
    private String planetOrigin;

    @NotBlank( message = "Destination is required")
    private String planetDestination;

}