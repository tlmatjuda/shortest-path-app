package za.co.discovery.assignment.thabomatjuda.soap.handler;


import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.thabomatjuda.constants.SpecialCharacters;
import za.co.discovery.assignment.thabomatjuda.model.ShortestPathResult;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;
import za.co.discovery.assignment.thabomatjuda.service.PlanetQueryService;
import za.co.discovery.assignment.thabomatjuda.service.PlanetService;
import za.co.discovery.assignment.thabomatjuda.service.ShortestPathCalculationService;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteRequest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteResponse;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripHop;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripInfo;

import java.util.List;

@Slf4j
@Component
public class ShortestPathHandler {

    public static final int ORIGIN_INDEX = 0;
    public static final int DESTINATION_INDEX = 1;

    private final ShortestPathCalculationService shortestPathCalculationService;
    private final PlanetQueryService planetQueryService;


    public ShortestPathHandler(ShortestPathCalculationService shortestPathCalculationService, PlanetQueryService planetQueryService) {
        this.shortestPathCalculationService = shortestPathCalculationService;
        this.planetQueryService = planetQueryService;
    }

    public RouteResponse calculate( RouteRequest routeRequest) {
        String origin = routeRequest.getOrigin();
        String destination = routeRequest.getDestination();

        ShortestPathResult calculationResult = shortestPathCalculationService.run(origin, destination);

        return buildRouteResponseBasedOnResults(calculationResult);
    }

    private RouteResponse buildRouteResponseBasedOnResults(ShortestPathResult calculationResult) {
        int tripIndex = 0;
        String calculationResultsText = calculationResult.getPath();
        String sanitisedCalcualtionsResultsText = sanitizeUnwantedCharacters( calculationResultsText);
        String[] tripHopsArray = sanitisedCalcualtionsResultsText.split(SpecialCharacters.COMMA);

        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setTotaldistance( calculationResult.getTotalDistance());
        List<TripHop> tripHops = routeResponse.getHops();

        for (String rawTripInfo : tripHopsArray) {
            TripHop tripHop = new TripHop();
            tripHop.setIndex(tripIndex);

            String[] singleHopPlanetNodesArray = rawTripInfo.split(SpecialCharacters.COLON);

            TripInfo originInfo = buildTripInfo( singleHopPlanetNodesArray[ORIGIN_INDEX].trim());
            tripHop.setTripStart(originInfo);

            TripInfo destinationInfo = buildTripInfo( singleHopPlanetNodesArray[DESTINATION_INDEX].trim());
            tripHop.setTripEnd(destinationInfo);

            tripHops.add(tripHop);
            tripIndex++;
        }

        return routeResponse;
    }

    private TripInfo buildTripInfo(String planetNode) {
        TripInfo originInfo = new TripInfo();
        PlanetModel planetModel = planetQueryService.fetchById(planetNode);
        originInfo.setNode(planetModel.getPlanetNode());
        originInfo.setName(planetModel.getPlanetName());
        return originInfo;
    }

    private static String sanitizeUnwantedCharacters( String rawData) {
        return rawData.replace(SpecialCharacters.SQUARE_BRACKET_OPEN, StringUtils.EMPTY)
                .replace(SpecialCharacters.SQUARE_BRACKET_CLOSE, StringUtils.EMPTY)
                .replace(SpecialCharacters.BRACKET_OPEN, StringUtils.EMPTY)
                .replace(SpecialCharacters.BRACKET_CLOSE, StringUtils.EMPTY);
    }
}
