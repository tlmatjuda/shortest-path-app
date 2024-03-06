package com.toob.service.shortest.soap.endpoint;


import com.toob.service.shortest.config.SoapWsConfig;
import com.toob.service.shortest.soap.handler.ShortestPathHandler;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteRequest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteResponse;



/**
 * The Entry Point of our SOAP webservices into our application.
 * @author : Thabo Matjuda
 */
@Endpoint
@Component
public class ShortestPathEndpoint {

    private final ShortestPathHandler shortestPathHandler;

    public ShortestPathEndpoint(ShortestPathHandler shortestPathHandler) {
        this.shortestPathHandler = shortestPathHandler;
    }


    /**
     * This handles a Route Request to calculate the Shortest path.
     * @param routeRequest : The request that has the Origin & Destination for the calculation.
     * @return : A {@link RouteResponse} that contains the total distance of the shortes path, together wit h a list of Hop or Sub-Trips
     */
    @PayloadRoot( namespace = SoapWsConfig.NAMESPACE_URI, localPart = "RouteRequest")
    @ResponsePayload
    public RouteResponse getShortestPath(@RequestPayload RouteRequest routeRequest) {
        return shortestPathHandler.calculate( routeRequest);
    }

}
