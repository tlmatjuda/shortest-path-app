package com.toob.service.shortest.util;

import com.toob.service.shortest.StartupProcesses;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
public abstract class AbstractTest {

    public static final String ENV_VAR_TESTCONTAINERS_RYUK_DISABLED = "TESTCONTAINERS_RYUK_DISABLED";

    @SystemStub
    protected static EnvironmentVariables ENV_VARS = new EnvironmentVariables(ENV_VAR_TESTCONTAINERS_RYUK_DISABLED, Boolean.TRUE.toString());

    @MockBean
    protected StartupProcesses startupProcesses;

}
