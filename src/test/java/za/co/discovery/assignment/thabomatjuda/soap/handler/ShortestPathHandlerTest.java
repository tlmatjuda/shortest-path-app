package za.co.discovery.assignment.thabomatjuda.soap.handler;

import com.toob.service.shortest.soap.handler.ShortestPathHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteRequest;
import za.co.discovery.assignment.thabomatjuda.soap.gen.RouteResponse;
import za.co.discovery.assignment.thabomatjuda.soap.gen.TripHop;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortestPathHandlerTest {

    @Autowired
    private ShortestPathHandler shortestPathHandler;

    @Test
    void shouldCalculateAndRespond() {
        RouteRequest routeRequest = new RouteRequest();
        routeRequest.setOrigin("A");
        routeRequest.setDestination("F");

        RouteResponse routeResponse = shortestPathHandler.calculate(routeRequest);
        assertNotNull( routeResponse);

        double totaldistance = routeResponse.getTotaldistance();
        assertEquals(2.38D, totaldistance);

        List<TripHop> tripHops = routeResponse.getHops();
        assertTrue( CollectionUtils.isNotEmpty( tripHops));
        assertEquals( 2, tripHops.size());
    }
}