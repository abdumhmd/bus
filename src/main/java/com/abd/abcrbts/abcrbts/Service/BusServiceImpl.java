package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Bus;
import com.abd.abcrbts.abcrbts.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;


    @Override
    public List<Bus> findAll() {
       return busRepository.findAll();
    }

    @Override
    public Bus save(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus findById(Integer id) {
        return busRepository.findBusById(id);
    }

    @Override
    public Bus findByPlate(String plate) {
        return busRepository.findBusByPlate(plate);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
            busRepository.deleteById(id);
    }

    @Override
    public List<Bus> findFree() {
            return busRepository.findFree();
    }

    @Override
    public Bus update(Bus bus) {
        bus.setAssigned(TRUE);
            return busRepository.save(bus);
    }
}
