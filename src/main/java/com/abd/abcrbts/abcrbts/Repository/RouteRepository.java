package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>{
    List<Route> findAll();
    Route save(Route route);
    Route findRouteById(Integer id);
    void deleteById(Integer id);
    List<Route> findRouteByDeparture(String departure);
    List<Route> findRouteByDestination(String destination);

}
