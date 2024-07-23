package com.toob.service.shortest;

import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.service.SupportDataFileService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class BaseRepoTest extends AbstractTest {

    @MockBean
    protected StartupProcesses startupProcesses;

    @MockBean
    protected CalculatorService calculatorService;

    @MockBean
    protected SupportDataFileService supportDataFileService;

    @MockBean
    protected Flyway flyway;
}
