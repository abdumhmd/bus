package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Schedule;
import com.abd.abcrbts.abcrbts.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findOne(id);
    }

    @Override
    public Schedule findByRoute(Route route) {
        return null;
    }

    @Override
    public Schedule update(Schedule schedule) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
