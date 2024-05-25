package com.toob.service.shortest.mapper;

import com.toob.service.shortest.AbstractSpringIntegrationTest;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.service.SupportDataFileService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@EnableAutoConfiguration( exclude = {
        FlywayAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
public abstract class AbstractMapperTest extends AbstractSpringIntegrationTest {

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @MockBean
    protected CalculatorService calculatorService;

    @MockBean
    protected PlanetRepository planetRepository;

    @MockBean
    protected RouteRepository routeRepository;

    @MockBean
    protected Flyway flyway;

}
