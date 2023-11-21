package za.co.discovery.assignment.thabomatjuda.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "Routes")
@Getter
@Setter
public class Route {

    @Id
    @Column(name = "RouteId")
    private Integer routeId;

    @ManyToOne
    @JoinColumn( name = "PlanetOrigin", referencedColumnName = "Node")
    private Planet planetOrigin;

    @ManyToOne
    @JoinColumn(name = "PlanetDestination", referencedColumnName = "Node")
    private Planet planetDestination;

    @Column(name = "DistanceInLightYears")
    private Double distanceInLightYears;

}