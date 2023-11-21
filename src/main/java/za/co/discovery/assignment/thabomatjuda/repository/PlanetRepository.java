package za.co.discovery.assignment.thabomatjuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;


/**
 * Database Mapping, Storing, Updating and Retrieval of Planet Objects
 * To be used for various Database Operations on the Planets Table
 * @author : Thabo Matjuda
 */
@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {
}