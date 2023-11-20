package za.co.discovery.assignment.thabomatjuda.soap.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import za.co.discovery.assignment.thabomatjuda.config.SoapWsConfig;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteRequest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteResponse;
import za.co.discovery.assignment.thabomatjuda.soap.handler.ShortestPathHandler;

@Endpoint
@Component
public class ShortestPathEndpoint {

    private final ShortestPathHandler shortestPathHandler;

    public ShortestPathEndpoint(ShortestPathHandler shortestPathHandler) {
        this.shortestPathHandler = shortestPathHandler;
    }

    @PayloadRoot( namespace = SoapWsConfig.NAMESPACE_URI, localPart = "RouteRequest")
    @ResponsePayload
    public RouteResponse getShortestPath(@RequestPayload RouteRequest routeRequest) {
        return shortestPathHandler.calculate( routeRequest);
    }

}
