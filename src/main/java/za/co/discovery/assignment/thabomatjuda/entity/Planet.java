package za.co.discovery.assignment.thabomatjuda.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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