package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> findAll() {
       return routeRepository.findAll();
    }

    @Override
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route findById(Integer id) {
        return routeRepository.findRouteById(id);
    }

    @Override
    public List<Route> findByDeparture(String departure) {
        return routeRepository.findRouteByDeparture(departure);
    }

    @Override
    public List<Route> findByDestination(String destination) {
        return routeRepository.findRouteByDestination(destination);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        routeRepository.deleteById(id);
    }

    @Override
    public List<Route> findDistinct() {
        return routeRepository.findDistinctByDeparture();
    }

    @Override
    public List<Route> findByDepartureAndDestination(String departure, String destination) {
        return routeRepository.findByDestinationAndDeparture(departure,destination);
    }
}
