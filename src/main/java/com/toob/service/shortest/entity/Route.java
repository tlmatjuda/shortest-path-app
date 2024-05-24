package com.toob.service.shortest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Represents our Routes Table.
 * @author : Thabo Matjuda
 */
@Entity
@Table( name = "routes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "node")
    private Planet origin;

    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "Node")
    private Planet destination;

    @Column(name = "distance")
    private Double distance;

}