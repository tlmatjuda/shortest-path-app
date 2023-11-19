package za.co.discovery.assignment.thabomatjuda.service;

import lombok.extern.slf4j.Slf4j;
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
    void shouldRun() {
        String shortestPathResults = shortestPathCalculationService.run("F");
        assertNotNull(shortestPathResults);

    }
}