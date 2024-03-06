package com.toob.service.shortest.model;

import lombok.Getter;
import lombok.Setter;



/**
 * This will carry our Calculation Result Data.
 * @author : Thabo Matjuda
 */
@Getter
@Setter
public class ShortestPathResult {

    private String path;
    private Double totalDistance;

}
