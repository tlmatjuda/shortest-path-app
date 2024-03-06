package za.co.discovery.assignment.thabomatjuda.config;

import com.toob.service.shortest.startup.BootstrapOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static za.co.discovery.assignment.thabomatjuda.config.BaseTestConfiguration.APP_BASE_PACKAGE;


@Slf4j
@TestConfiguration
@ComponentScan(
        basePackages = {APP_BASE_PACKAGE},
        excludeFilters = {
            @ComponentScan.Filter( type= FilterType.ASSIGNABLE_TYPE, value= BootstrapOperations.class)
        }
)
public class BaseTestConfiguration {

    public static final String APP_BASE_PACKAGE = "za.co.discovery.assignment.thabomatjuda";


}