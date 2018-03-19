package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Bus;

import java.util.List;

public interface BusService {
    public List<Bus> findAll();
    public Bus save(Bus bus);
    public Bus findById(Integer id);
    public Bus findByPlate(String plate);
    public void deleteById(Integer id);
    public List<Bus> findFree();
    public Bus update(Bus bus);
}
