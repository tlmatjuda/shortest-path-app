package za.co.discovery.assignment.thabomatjuda.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.repository.RouteRepository;
import za.co.discovery.assignment.thabomatjuda.service.SupportDataFileService;


@Slf4j
@Component
public class BootstrapOperations implements CommandLineRunner {


    private final SupportDataFileService supportDataFileService;

    public BootstrapOperations(SupportDataFileService supportDataFileService) {
        this.supportDataFileService = supportDataFileService;
    }

    @Override
    public void run(String... args) throws Exception {
       supportDataFileService.process();
    }


}
