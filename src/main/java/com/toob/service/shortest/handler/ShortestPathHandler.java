package com.toob.service.shortest.handler;


import com.toob.service.shortest.constants.SpecialCharacters;
import com.toob.service.shortest.model.*;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.service.PlanetQueryService;
import com.toob.service.shortest.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    private final CalculatorService calculatorService;
    private final PlanetQueryService planetQueryService;

    /**
     * Contructor injection of the required dependencies.
     * @param calculatorService
     * @param planetQueryService
     */
    public ShortestPathHandler(CalculatorService calculatorService, PlanetQueryService planetQueryService) {
        this.calculatorService = calculatorService;
        this.planetQueryService = planetQueryService;
    }

    /**
     * Helper entry into our Business Logic call to calculate the shortest path.
     * @param routeRequest : Takes in this request on unpack the required arguments.
     * @return
     */
    public JsonRouteResponse calculate(JsonRouteRequest routeRequest) {
        String origin = routeRequest.getOrigin();
        String destination = routeRequest.getDestination();

        ShortestPathResult calculationResult = calculatorService.run(origin, destination);

        return buildRouteResponseBasedOnResults(calculationResult);
    }

    /**
     * Uses the obtained Responses to build up a response.
     * @param calculationResult : The required result in order to build up a response.
     * @return
     */
    private JsonRouteResponse buildRouteResponseBasedOnResults(ShortestPathResult calculationResult) {
        String calculationResultsText = calculationResult.getPath();

        // Cleanup the response
        String sanitisedCalculationsResultsText = sanitizeUnwantedCharacters( calculationResultsText);

        // Split up the Hops into an Array
        String[] tripHopsArray = sanitisedCalculationsResultsText.split(SpecialCharacters.COMMA);

        // Prepare the response and start setting all the data we need.
        JsonRouteResponse routeResponse = new JsonRouteResponse();
        routeResponse.setTotalDistanceInLightYears( calculationResult.getTotalDistance());
        routeResponse.setSubTrips( accumulateSubtrips( tripHopsArray));

        return routeResponse;
    }

    private List<JsonTripHop> accumulateSubtrips(String[] tripHopsArray) {
        int tripIndex = 0;
        List<JsonTripHop> tripHops = new ArrayList<>();

        for (String rawTripInfo : tripHopsArray) {
            JsonTripHop tripHop = new JsonTripHop();
            tripHop.setIndex(tripIndex);

            // Split up into the Origin & Destination Nodes.
            String[] singleHopPlanetNodesArray = rawTripInfo.split(SpecialCharacters.COLON);

            // Look up and build a Hop Object for the Origin
            JsonTripInfo originInfo = buildTripInfo( singleHopPlanetNodesArray[ORIGIN_INDEX].trim());
            tripHop.setTripStart(originInfo);

            // Look up and build a Hop Object for the Destination
            JsonTripInfo destinationInfo = buildTripInfo( singleHopPlanetNodesArray[DESTINATION_INDEX].trim());
            tripHop.setTripEnd(destinationInfo);

            tripHops.add(tripHop);
            tripIndex++;
        }

        return tripHops;
    }

    /**
     * Builds up the Trip Hop information.
     * This will be Origin & Destination
     * @param planetNode : Source node to use when building up information.
     * @return
     */
    private JsonTripInfo buildTripInfo(String planetNode) {
        JsonTripInfo originInfo = new JsonTripInfo();
        PlanetModel planetModel = planetQueryService.fetchById(planetNode);
        originInfo.setNode(planetModel.getNode());
        originInfo.setName(planetModel.getName());
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
