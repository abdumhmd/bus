package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>{
    List<Route> findAll();
    Route save(Route route);
    Route findRouteById(Integer id);
    void deleteById(Integer id);

    @Query("SELECT DISTINCT r FROM Route r  GROUP BY (r.departure)")
    List<Route> findDistinctByDeparture();
    List<Route> findRouteByDeparture(String departure);
    List<Route> findRouteByDestination(String destination);
    Route findById(Integer id);
    List<Route> findByDestinationAndDeparture(String departure,String destination);

}
