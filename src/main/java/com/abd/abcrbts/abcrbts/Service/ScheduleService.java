package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Schedule;

import java.util.List;

public interface ScheduleService {
    public Schedule save(Schedule schedule);
    public List<Schedule> findAll();
    public Schedule findById(Long id);
    public Schedule findByRoute(Route route);
    public Schedule update(Schedule schedule);
    public void deleteById(Long id);
}
