package com.toob.service.shortest.service;

import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.constants.RoutesConstants;
import com.toob.service.shortest.exception.ShortestPathCalculationException;
import com.toob.service.shortest.model.ShortestPathResult;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.util.MockedPlanetsUtil;
import com.toob.service.shortest.util.MockedRoutesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class CalculatorServiceTest extends AbstractNonDatabaseIntegrationServiceTest {

    @Autowired
    private CalculatorService calculatorService;


    @Test
    void shouldCalculateShortestPath() {
        when(planetRepository.findAll()).thenReturn(MockedPlanetsUtil.fetchAll());
        when(routeRepository.findAll()).thenReturn(MockedRoutesUtil.fetchAll());
        calculatorService.initialise();

        final String originKey = "A";
        final String destinationKey = "F";
        ShortestPathResult calculationResponse = calculatorService.run(originKey, destinationKey);
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
                () -> calculatorService.run(StringUtils.EMPTY, StringUtils.EMPTY));
        assertEquals(RoutesConstants.ERROR_REQUEST_BODY_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void shouldFailWhenDestinationDoesNotExist() {
        Throwable thrown = assertThrows(ShortestPathCalculationException.class,
                () -> calculatorService.run("AZ", "ZA"));

        assertEquals(RoutesConstants.ERROR_DESTINATION_NOT_FOUND, thrown.getMessage());
    }



    /**
     * Mocking Beans we don't want
     */
    @MockBean
    private PlanetRepository planetRepository;

    @MockBean
    private RouteRepository routeRepository;

    @MockBean
    private StartupProcesses startupProcesses;

    @MockBean
    private SupportDataFileService supportDataFileService;

    @MockBean
    private Flyway flyway;

}