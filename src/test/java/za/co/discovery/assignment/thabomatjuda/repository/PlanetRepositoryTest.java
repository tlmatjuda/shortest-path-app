package za.co.discovery.assignment.thabomatjuda.repository;

import com.toob.service.shortest.entity.Planet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class PlanetRepositoryTest extends BaseRepositoryTest {

    @Test
    void shouldFindPlanets() {
        List<Planet> planetsList = planetRepository.findAll();
        assertTrue( CollectionUtils.isNotEmpty( planetsList));
    }
}