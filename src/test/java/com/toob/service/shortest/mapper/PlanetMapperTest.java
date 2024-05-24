package com.toob.service.shortest.mapper;

import com.google.common.collect.Lists;
import com.toob.service.shortest.StartupProcesses;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.mapper.PlanetMapper;
import com.toob.service.shortest.model.planet.PlanetModel;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import com.toob.service.shortest.service.SupportDataFileService;
import com.toob.service.shortest.util.MockedPlanetsUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@EnableAutoConfiguration( exclude = {
        FlywayAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
class PlanetMapperTest {

    /**
     * Mocking Beans we don't want
     */
    @MockBean
    private PlanetRepository planetRepository;

    @MockBean
    private RouteRepository routeRepository;

    @MockBean
    private StartupProcesses startupProcesses;

    @MockBean
    private SupportDataFileService supportDataFileService;

    @MockBean
    private Flyway flyway;

    @Autowired
    private PlanetMapper planetMapper;

    @Test
    void shouldMap() {
        Planet planet = MockedPlanetsUtil.fetchByNode("A");
        PlanetModel model = planetMapper.asModel(planet);

        assertNotNull( model);
        assertEquals( planet.getNode(), model.getNode());
        assertEquals( planet.getName(), model.getName());

        Planet entity = planetMapper.asEntity(model);
        assertNotNull( entity);
    }

    @Test
    void shouldMapCollection() {
        ArrayList<Planet> planets = Lists.newArrayList(MockedPlanetsUtil.fetchByNode("A"), MockedPlanetsUtil.fetchByNode("B"));
        List<PlanetModel> modelList = planetMapper.asModel(planets);

        assertEquals( planets.size(), modelList.size());
        List<Planet> entityList = planetMapper.asEntity(modelList);
        assertTrue( CollectionUtils.isNotEmpty( entityList));
    }
}