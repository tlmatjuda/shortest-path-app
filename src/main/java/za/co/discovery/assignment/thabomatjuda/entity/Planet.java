package za.co.discovery.assignment.thabomatjuda.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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