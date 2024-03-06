package za.co.discovery.assignment.thabomatjuda.model;

import com.toob.service.shortest.model.TechnicalResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnicalResponseTest {

    @Test
    void shouldBuildResponse() {

        TechnicalResponse info = TechnicalResponse.info("Good Info", "We believe this is going well");
        info.setInfo("More Info");
        info.setDescription("Info Described Here");
        assertNotNull( info);

        TechnicalResponse error = TechnicalResponse.error("Error Happened", "Let's kepp trying");
        error.setInfo("Error Getting Better");
        error.setDescription("Things are really getting better, just not there yet");
        assertNotNull( error);

    }
}