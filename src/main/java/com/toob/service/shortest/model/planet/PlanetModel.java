package com.toob.service.shortest.model.planet;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Planet DTO that will carry data to and from the outside world via our REST API
 * @author : Thabo Matjuda
 */
@Getter
@Setter
@NotNull( message = "Planet data is required")
public class PlanetModel implements Serializable {

    @NotBlank( message = "PlanetNode is required")
    private String planetNode;

    @NotBlank( message = "PlanetName is required")
    private String planetName;

}