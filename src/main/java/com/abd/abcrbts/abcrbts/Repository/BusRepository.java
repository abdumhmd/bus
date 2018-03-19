package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    List<Bus> findAll();
    Bus save(Bus bus);
    Bus findBusById(Integer id);
    void deleteById(Integer id);
    Bus findBusByPlate(String plate);
    @Query("SELECT b from Bus b where b.assigned = FALSE")
    List<Bus> findFree();

}
