package com.toob.service.shortest.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;


/**
 * Represents our PlanetNames table.
 * @author : Thabo Matjuda
 */
@Entity
@Table(name = "PlanetNames")
@Getter
@Setter
public class Planet implements Serializable {

    @Id
    @Column(name = "Node", unique = true)
    private String planetNode;

    @Column(name = "Name")
    private String planetName;

}