//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.28 at 12:44:23 AM SAST 
//


package com.toob.service.shortest.soap.gen;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.co.discovery.assignment.thabomatjuda.soap.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.co.discovery.assignment.thabomatjuda.soap.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RouteRequest }
     * 
     */
    public RouteRequest createRouteRequest() {
        return new RouteRequest();
    }

    /**
     * Create an instance of {@link RouteResponse }
     * 
     */
    public RouteResponse createRouteResponse() {
        return new RouteResponse();
    }

    /**
     * Create an instance of {@link TripHop }
     * 
     */
    public TripHop createTripHop() {
        return new TripHop();
    }

    /**
     * Create an instance of {@link TripInfo }
     * 
     */
    public TripInfo createTripInfo() {
        return new TripInfo();
    }

}
