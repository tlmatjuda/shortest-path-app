package com.toob.service.shortest;

import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.service.SupportDataFileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@Slf4j
@SpringBootTest
@Import(TestConfigFactory.class)
public abstract class AbstractSpringIntegrationTest extends AbstractTest {

    @MockBean
    protected StartupProcesses startupProcesses;

}
