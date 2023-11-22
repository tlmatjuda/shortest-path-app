package za.co.discovery.assignment.thabomatjuda.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.thabomatjuda.constants.RoutesConstants;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.exception.ShortestPathCalculationException;
import za.co.discovery.assignment.thabomatjuda.model.ShortestPathResult;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.repository.RouteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


/**
 * The business logic for calcualtion of the shortest path.
 * This makes use of the Dijkstra Algorithm to calculate the shortest path.
 * @author : Thabo Matjuda
 */
@Slf4j
@Service
@Transactional
public class ShortestPathCalculationService {

    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;
    private final SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    /**
     * Constructor injecttion of required dependencies.
     * @param planetRepository
     * @param routeRepository
     */
    public ShortestPathCalculationService(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }

    /**
     * Initializing of the Graph for calcutlation.
     */
    public void initialise() {
        addVertices();
        addEdges();
    }

    /**
     * The core entry point into this service.
     * This is where we start the calcualion of the shortest path.
     * @param origin
     * @param destination
     * @return
     */
    public ShortestPathResult run(String origin, String destination) {
        ShortestPathResult calculationResponse = new ShortestPathResult();

        // Validation to check if destination has been provided
        if (StringUtils.isBlank( origin) || StringUtils.isBlank( destination)) {
            log.info(RoutesConstants.ERROR_REQUEST_BODY_NOT_FOUND);
            throw new ShortestPathCalculationException(RoutesConstants.ERROR_REQUEST_BODY_NOT_FOUND);
        }

        // Validating that given destination exists and that it is not the same as the origin
        if (graph.containsVertex(destination) && !destination.equals(RoutesConstants.ORIGIN_NODE)) {
            GraphPath<String, DefaultWeightedEdge> calculationResults = DijkstraShortestPath.findPathBetween(graph, origin, destination);
            calculationResponse.setPath( calculationResults.toString());
            calculationResponse.setTotalDistance( calculationResults.getWeight());
            log.info("The shortest path found : {}, with total distance of : {}",
                    calculationResponse.getPath(), calculationResponse.getTotalDistance());
        // Let's also make sure we have what we are looking for. The destination must exist
        } else {
            log.info(RoutesConstants.ERROR_DESTINATION_NOT_FOUND);
            throw new ShortestPathCalculationException(RoutesConstants.ERROR_DESTINATION_NOT_FOUND);
        }

        return calculationResponse;
    }

    /**
     * Setting Graph vertices
     * Getting ready for calcaulations
     */
    private void addVertices() {
        List<Planet> planets = planetRepository.findAll();
        if (CollectionUtils.isNotEmpty(planets)) {
            planets.forEach(planet -> graph.addVertex(planet.getPlanetNode()));
        }
    }

    /**
     * Setting Graph edged
     * Getting ready for calculations as well.
     */
    private void addEdges() {
        AtomicReference<DefaultWeightedEdge> edge = new AtomicReference<>();
        List<Route> routeList = routeRepository.findAll();

        routeList.forEach( routeRecord -> {
            String originNode = (Objects.nonNull(routeRecord.getPlanetOrigin())) ? routeRecord.getPlanetOrigin().getPlanetNode() : StringUtils.EMPTY;
            String destinationNode = (Objects.nonNull(routeRecord.getPlanetDestination())) ? routeRecord.getPlanetDestination().getPlanetNode() : StringUtils.EMPTY;

            // Validating that edge nodes are different
            if (!originNode.equals(destinationNode)) {
                edge.set( graph.addEdge(originNode, destinationNode));
            }
            // Adding edge weight
            addEdgeWeight( edge.get(), routeRecord.getDistanceInLightYears());
        });
    }

    /**
     * etting Graph edge weight
     * @param edge
     * @param weight
     */
    private void addEdgeWeight(DefaultWeightedEdge edge, double weight) {
        graph.setEdgeWeight(edge, weight);
    }


}
