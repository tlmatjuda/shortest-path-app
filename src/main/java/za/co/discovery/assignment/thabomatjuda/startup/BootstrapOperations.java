package za.co.discovery.assignment.thabomatjuda.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.thabomatjuda.service.ShortestPathCalculationService;
import za.co.discovery.assignment.thabomatjuda.service.SupportDataFileService;


/**
 * The initialisation of various states, configurations
 * This is the startup process logic
 * @author : Thabo Matjuda
 */
@Slf4j
@Component
public class BootstrapOperations implements CommandLineRunner {


    private final SupportDataFileService supportDataFileService;
    private final ShortestPathCalculationService shortestPathCalculationService;

    /**
     * Constructor injection of required depenedencies
     * @param supportDataFileService :
     * @param shortestPathCalculationService
     */
    public BootstrapOperations(SupportDataFileService supportDataFileService, ShortestPathCalculationService shortestPathCalculationService) {
        this.supportDataFileService = supportDataFileService;
        this.shortestPathCalculationService = shortestPathCalculationService;
    }

    /**
     * Entry point of the initialisation operations we want to perform at service startup.
     * @param args : whatever args we m ay be passing from the CLI.
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        // Calls the component that will deal with reading the Excel file and importing it into our database.
       supportDataFileService.process();

        // Initialises the calculation information that we will need when using the logic in here.
       shortestPathCalculationService.initialise();
    }


}
