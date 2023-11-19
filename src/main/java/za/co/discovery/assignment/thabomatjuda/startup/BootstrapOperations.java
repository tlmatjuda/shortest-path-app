package za.co.discovery.assignment.thabomatjuda.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.thabomatjuda.service.ShortestPathCalculationService;
import za.co.discovery.assignment.thabomatjuda.service.SupportDataFileService;


@Slf4j
@Component
public class BootstrapOperations implements CommandLineRunner {


    private final SupportDataFileService supportDataFileService;
    private final ShortestPathCalculationService shortestPathCalculationService;

    public BootstrapOperations(SupportDataFileService supportDataFileService, ShortestPathCalculationService shortestPathCalculationService) {
        this.supportDataFileService = supportDataFileService;
        this.shortestPathCalculationService = shortestPathCalculationService;
    }

    @Override
    public void run(String... args) throws Exception {
       supportDataFileService.process();
       shortestPathCalculationService.initialise();
    }


}
