package za.co.discovery.assignment.thabomatjuda.soap.endpoint;


import jakarta.xml.bind.JAXBElement;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import za.co.discovery.assignment.thabomatjuda.config.SoapWsConfig;
import za.co.discovery.assignment.thabomatjuda.soap.gen.GetShortestPathRequest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.GetShortestPathResponse;

import javax.xml.namespace.QName;

@Endpoint
@Component
public class ShortestPathEndpoint {

    @PayloadRoot( namespace = SoapWsConfig.NAMESPACE_URI, localPart = "GetShortestPathRequest")
    @ResponsePayload
    public JAXBElement<GetShortestPathResponse> getShortestPath(@RequestPayload JAXBElement<GetShortestPathRequest> request) {
        GetShortestPathResponse response = new GetShortestPathResponse();
        response.setTotaldistance(2.38D);

        return createJaxbElement(response, GetShortestPathResponse.class);
    }

    private <T> JAXBElement<T> createJaxbElement(T object, Class<T> clazz) {
        return new JAXBElement<>(new QName(clazz.getSimpleName()), clazz, object);
    }

}
