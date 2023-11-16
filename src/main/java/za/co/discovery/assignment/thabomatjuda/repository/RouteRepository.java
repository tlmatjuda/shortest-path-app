package za.co.discovery.assignment.thabomatjuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.thabomatjuda.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
}