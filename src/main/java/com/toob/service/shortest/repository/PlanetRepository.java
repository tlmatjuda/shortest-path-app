package com.toob.service.shortest.repository;

import com.toob.service.shortest.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Database Mapping, Storing, Updating and Retrieval of Planet Objects
 * To be used for various Database Operations on the Planets Table
 * @author : Thabo Matjuda
 */
@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {
}