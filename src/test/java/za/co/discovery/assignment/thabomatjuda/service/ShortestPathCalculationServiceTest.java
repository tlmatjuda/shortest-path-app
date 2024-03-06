package za.co.discovery.assignment.thabomatjuda.service;

import com.toob.service.shortest.constants.RoutesConstants;
import com.toob.service.shortest.exception.ShortestPathCalculationException;
import com.toob.service.shortest.model.ShortestPathResult;
import com.toob.service.shortest.service.ShortestPathCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class ShortestPathCalculationServiceTest {

    @Autowired
    private ShortestPathCalculationService shortestPathCalculationService;

    @Test
    void shouldCalculateShortestPath() {
        final String originKey = "A";
        final String destinationKey = "F";
        ShortestPathResult calculationResponse = shortestPathCalculationService.run(originKey, destinationKey);
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
                () -> shortestPathCalculationService.run(StringUtils.EMPTY, StringUtils.EMPTY));
        assertEquals(RoutesConstants.ERROR_REQUEST_BODY_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void shouldFailWhenDestinationDoesNotExist() {
        Throwable thrown = assertThrows(ShortestPathCalculationException.class,
                () -> shortestPathCalculationService.run("AZ", "ZA"));

        assertEquals(RoutesConstants.ERROR_DESTINATION_NOT_FOUND, thrown.getMessage());
    }
}