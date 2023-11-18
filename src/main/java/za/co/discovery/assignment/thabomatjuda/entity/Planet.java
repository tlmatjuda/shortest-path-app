package za.co.discovery.assignment.thabomatjuda.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PlanetNames")
@Data
public class Planet implements Serializable {

    @Id
    @Column(name = "Node", unique = true)
    private String planetNode;

    @Column(name = "Name")
    private String planetName;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Route> routes = new HashSet<>();

}