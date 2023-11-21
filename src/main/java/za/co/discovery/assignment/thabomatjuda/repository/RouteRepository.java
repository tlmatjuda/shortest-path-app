package za.co.discovery.assignment.thabomatjuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.thabomatjuda.entity.Route;

import java.util.List;


/**
 * Database Mapping, Storing, Updating and Retrieval of Planet Objects
 * To be used for various Database Operations on the Routes Table
 * @author : Thabo Matjuda
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Modifying
    @Query("DELETE FROM Route r WHERE r.planetOrigin.planetNode = :originNode OR r.planetDestination.planetNode = :destinationNode")
    void deleteRoutesByPlanetOriginAndPlanetDestination(String originNode, String destinationNode);

    @Query("SELECT r FROM Route r WHERE r.planetOrigin.planetNode = :originNode OR r.planetDestination.planetNode = :destinationNode")
    List<Route> findByPlanetOriginAndPlanetDestination( String originNode, String destinationNode);

}