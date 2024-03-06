package com.toob.service.shortest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents our Routes Table.
 * @author : Thabo Matjuda
 */
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