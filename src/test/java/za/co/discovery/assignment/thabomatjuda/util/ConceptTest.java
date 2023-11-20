package za.co.discovery.assignment.thabomatjuda.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.Test;
import za.co.discovery.assignment.thabomatjuda.constants.SpecialCharacters;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteResponse;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripHop;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripInfo;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConceptTest {


    public static final int ORIGIN_INDEX = 0;
    public static final int DESTINATION_INDEX = 1;

    @Test
    void shouldCleanout() {
        final String THE_TEXT = "[(A : C), (C : F)]";
        String cleaned = sanitizeUnwantedCharacters(THE_TEXT);
        assertNotNull(cleaned);

        String[] tripInfoText = cleaned.split(SpecialCharacters.COMMA);
        int tripIndex = 0;

        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setTotaldistance(2.36D);

        List<TripHop> tripHops = routeResponse.getHops();

        for (String rawTripInfo : tripInfoText) {
            String[] planetNodesHop = rawTripInfo.split(SpecialCharacters.COLON);

            TripInfo originInfo = new TripInfo();
            originInfo.setNode(planetNodesHop[ORIGIN_INDEX].trim());

            TripInfo destinationInfo = new TripInfo();
            destinationInfo.setNode(planetNodesHop[DESTINATION_INDEX].trim());

            TripHop tripHop = new TripHop();
            tripHop.setIndex(tripIndex);
            tripHop.setTripStart(originInfo);
            tripHop.setTripEnd(destinationInfo);

            tripHops.add(tripHop);
        }

        assertTrue(CollectionUtils.isNotEmpty( tripHops));
    }

    private static String sanitizeUnwantedCharacters( String rawData) {
        return rawData.replace(SpecialCharacters.SQUARE_BRACKET_OPEN, StringUtils.EMPTY)
                .replace(SpecialCharacters.SQUARE_BRACKET_CLOSE, StringUtils.EMPTY)
                .replace(SpecialCharacters.BRACKET_OPEN, StringUtils.EMPTY)
                .replace(SpecialCharacters.BRACKET_CLOSE, StringUtils.EMPTY);
    }
}