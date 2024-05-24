package com.toob.service.shortest.service;

import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration( exclude = {
        FlywayAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
public abstract class AbstractNonDatabaseIntegrationServiceTest {}
