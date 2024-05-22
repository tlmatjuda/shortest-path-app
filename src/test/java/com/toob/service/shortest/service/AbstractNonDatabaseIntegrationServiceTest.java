package com.toob.service.shortest.service;

import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@EnableAutoConfiguration( exclude = {
        FlywayAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
public abstract class AbstractNonDatabaseIntegrationServiceTest {


}
