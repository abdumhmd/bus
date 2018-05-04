package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Route;

import java.util.List;

public interface RouteService {
    public List<Route> findAll();
    public Route save(Route route);
    public Route findById(Integer id);
    public List<Route> findByDeparture(String departure);
    public List<Route> findByDestination(String destination);
    public void deleteById(Integer id);
    public List<Route> findDistinct();
    public List<Route> findByDepartureAndDestination(String departure,String destination);
    public List<Route> dataTable();
}
