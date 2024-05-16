package com.toob.service.shortest;

import com.toob.service.shortest.service.CalculationService;
import com.toob.service.shortest.service.SupportDataFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * The initialisation of various states, configurations
 * This is the startup process logic
 * @author : Thabo Matjuda
 */
@Slf4j
@Component
public class StartupProcesses implements CommandLineRunner {


    private final SupportDataFileService supportDataFileService;
    private final CalculationService calculationService;

    /**
     * Constructor injection of required depenedencies
     * @param supportDataFileService :
     * @param calculationService
     */
    public StartupProcesses(SupportDataFileService supportDataFileService, CalculationService calculationService) {
        this.supportDataFileService = supportDataFileService;
        this.calculationService = calculationService;
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
       calculationService.initialise();
    }
}
