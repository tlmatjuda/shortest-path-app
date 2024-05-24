package com.toob.service.shortest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;


/**
 * Represents our PlanetNames table.
 * @author : Thabo Matjuda
 */
@Entity
@Table(name = "planets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Planet implements Serializable {

    @Id
    @Column(name = "node", length = 3, unique = true, nullable = false)
    private String node;

    @Column(name = "name", length = 100, unique = true, nullable = false)
    private String name;

}