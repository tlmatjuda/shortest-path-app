package com.toob.service.shortest.soap.handler;


import com.toob.service.shortest.constants.SpecialCharacters;
import com.toob.service.shortest.model.ShortestPathResult;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.service.PlanetQueryService;
import com.toob.service.shortest.service.ShortestPathCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteRequest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteResponse;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripHop;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripInfo;

import java.util.List;


/**
 * A Supporting Handler of our Webs Service.
 * This will take in the incoming request for the Shortest Path Calculation and then cal our business logic for that.
 * It's like a middle man betwen our business logic and the web service entry point.
 * @author : Thabo Matjuda
 */
@Slf4j
@Component
public class ShortestPathHandler {

    public static final int ORIGIN_INDEX = 0;
    public static final int DESTINATION_INDEX = 1;

    private final ShortestPathCalculationService shortestPathCalculationService;
    private final PlanetQueryService planetQueryService;

    /**
     * Contructor injection of the required dependencies.
     * @param shortestPathCalculationService
     * @param planetQueryService
     */
    public ShortestPathHandler(ShortestPathCalculationService shortestPathCalculationService, PlanetQueryService planetQueryService) {
        this.shortestPathCalculationService = shortestPathCalculationService;
        this.planetQueryService = planetQueryService;
    }

    /**
     * Helper entry into our Business Logic call to calculate the shortest path.
     * @param routeRequest : Takes in this request on unpack the required arguments.
     * @return
     */
    public RouteResponse calculate( RouteRequest routeRequest) {
        String origin = routeRequest.getOrigin();
        String destination = routeRequest.getDestination();

        ShortestPathResult calculationResult = shortestPathCalculationService.run(origin, destination);

        return buildRouteResponseBasedOnResults(calculationResult);
    }

    /**
     * Uses the obtained Responses to build up a response.
     * @param calculationResult : The required result in order to build up a response.
     * @return
     */
    private RouteResponse buildRouteResponseBasedOnResults(ShortestPathResult calculationResult) {
        int tripIndex = 0;
        String calculationResultsText = calculationResult.getPath();

        // Cleanup the response
        String sanitisedCalculationsResultsText = sanitizeUnwantedCharacters( calculationResultsText);

        // Split up the Hops into an Array
        String[] tripHopsArray = sanitisedCalculationsResultsText.split(SpecialCharacters.COMMA);

        // Prepare the response and start setting all the data we need.
        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setTotaldistance( calculationResult.getTotalDistance());
        List<TripHop> tripHops = routeResponse.getHops();

        for (String rawTripInfo : tripHopsArray) {
            TripHop tripHop = new TripHop();
            tripHop.setIndex(tripIndex);

            // Split up into the Origin & Destination Nodes.
            String[] singleHopPlanetNodesArray = rawTripInfo.split(SpecialCharacters.COLON);

            // Look up and build a Hop Object for the Origin
            TripInfo originInfo = buildTripInfo( singleHopPlanetNodesArray[ORIGIN_INDEX].trim());
            tripHop.setTripStart(originInfo);

            // Look up and build a Hop Object for the Destination
            TripInfo destinationInfo = buildTripInfo( singleHopPlanetNodesArray[DESTINATION_INDEX].trim());
            tripHop.setTripEnd(destinationInfo);

            tripHops.add(tripHop);
            tripIndex++;
        }

        return routeResponse;
    }

    /**
     * Builds up the Trip Hop information.
     * This will be Origin & Destination
     * @param planetNode : Source node to use when building up information.
     * @return
     */
    private TripInfo buildTripInfo(String planetNode) {
        TripInfo originInfo = new TripInfo();
        PlanetModel planetModel = planetQueryService.fetchById(planetNode);
        originInfo.setNode(planetModel.getPlanetNode());
        originInfo.setName(planetModel.getPlanetName());
        return originInfo;
    }

    /**
     * Cleans up our calculated restult text i.e. something like : [(A : C), (C : F)]
     * @param rawData : The arguemnt to clean out.
     * @return
     */
    private static String sanitizeUnwantedCharacters( String rawData) {
        return rawData.replace(SpecialCharacters.SQUARE_BRACKET_OPEN, StringUtils.EMPTY)
                .replace(SpecialCharacters.SQUARE_BRACKET_CLOSE, StringUtils.EMPTY)
                .replace(SpecialCharacters.BRACKET_OPEN, StringUtils.EMPTY)
                .replace(SpecialCharacters.BRACKET_CLOSE, StringUtils.EMPTY);
    }
}
