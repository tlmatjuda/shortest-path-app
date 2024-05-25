package com.toob.service.shortest.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.*;


@Getter
@Setter
public class JsonRouteRequest {

    protected String origin;
    protected String destination;

}
