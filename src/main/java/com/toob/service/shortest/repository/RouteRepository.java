package com.toob.service.shortest.repository;

import com.toob.service.shortest.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Database Mapping, Storing, Updating and Retrieval of Planet Objects
 * To be used for various Database Operations on the Routes Table
 * @author : Thabo Matjuda
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Route r WHERE r.origin.node = :originNode OR r.destination.node = :destinationNode")
    void deleteRoutesByPlanetOriginAndPlanetDestination(@Param("originNode") String originNode, @Param("destinationNode") String destinationNode);

    @Query("SELECT r FROM Route r WHERE r.origin.node = :originNode OR r.destination.node = :destinationNode")
    List<Route> findByPlanetOriginAndPlanetDestination(@Param("originNode") String originNode, @Param("destinationNode")  String destinationNode);

}