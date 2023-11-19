package za.co.discovery.assignment.thabomatjuda.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.constants.RoutesConstants;
import za.co.discovery.assignment.thabomatjuda.exception.ShortestPathCalculationException;
import za.co.discovery.assignment.thabomatjuda.model.ShortestPathResult;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class ShortestPathCalculationServiceTest {

    @Autowired
    private ShortestPathCalculationService shortestPathCalculationService;

    @Test
    void shouldCalculateShortestPath() {
        final String saturnId = "F";
        ShortestPathResult calculationResponse = shortestPathCalculationService.run(saturnId);
        assertNotNull( calculationResponse);

        String path = calculationResponse.getPath();
        assertNotNull( path);
        assertTrue( path.contains("(A : C)"));
        assertTrue( path.contains("(C : F)"));

        Double totalDistance = calculationResponse.getTotalDistance();
        assertNotNull( totalDistance);
        assertEquals(2.38D, totalDistance);
    }

    @Test
    void shouldFailWhenArgumentIsMissing() {
        Throwable thrown = assertThrows(ShortestPathCalculationException.class,
                () -> shortestPathCalculationService.run(StringUtils.EMPTY));
        assertEquals(RoutesConstants.ERROR_REQUEST_BODY_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void shouldFailWhenDestinationIsSameAsOrigin() {
        Throwable thrown = assertThrows(ShortestPathCalculationException.class,
                () -> shortestPathCalculationService.run(RoutesConstants.ORIGIN_NODE));

        assertEquals(RoutesConstants.ERROR_DESTINATION_EQUAL_TO_ORIGIN, thrown.getMessage());
    }

    @Test
    void shouldFailWhenDestinationDoesNotExist() {
        Throwable thrown = assertThrows(ShortestPathCalculationException.class,
                () -> shortestPathCalculationService.run("AZ"));

        assertEquals(RoutesConstants.ERROR_DESTINATION_NOT_FOUND, thrown.getMessage());
    }
}