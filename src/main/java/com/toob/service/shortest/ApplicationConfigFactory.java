package com.toob.service.shortest;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring Configuration for REST API.
 * @author : Thabo Matjuda
 */
@Configuration
@EnableWebMvc
public class ApplicationConfigFactory {

    private final Environment environment;

    public ApplicationConfigFactory(Environment environment) {
        this.environment = environment;
    }

    @Bean( initMethod = "migrate")
    public Flyway flyway() {
        String dbUrl = environment.getRequiredProperty("spring.datasource.url");
        String dbUserName = environment.getRequiredProperty("spring.datasource.username");
        String dbUserPassword = environment.getRequiredProperty("spring.datasource.password");
        return new Flyway( Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(dbUrl, dbUserName, dbUserPassword)
                .locations("classpath:database/migration"));
    }

}
