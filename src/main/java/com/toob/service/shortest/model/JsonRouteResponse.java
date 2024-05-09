package com.toob.service.shortest.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class JsonRouteResponse {

    protected double totalDistanceInLightYears;
    protected List<JsonTripHop> subTrips;

}