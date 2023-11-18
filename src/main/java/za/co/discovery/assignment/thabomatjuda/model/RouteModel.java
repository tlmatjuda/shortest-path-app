package za.co.discovery.assignment.thabomatjuda.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class RouteModel implements Serializable  {

    private Integer routeId;
    private PlanetModel planetOrigin;
    private PlanetModel planetDestination;
    private Double distanceInLightYears;

}