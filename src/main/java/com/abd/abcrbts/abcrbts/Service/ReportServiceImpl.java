package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Tickets> byAgent(Agents agent, Date start, Date end) {
        return reportRepository.findByAgentsAndTimeSoldBetween(agent,start,end);
    }

    @Override
    public List<Tickets> byTicketOfficer(Users users, Date start, Date end) {
        return reportRepository.findBySoldByAndTimeSoldBetween(users,start,end);
    }

    @Override
    public List<Tickets> byRoute(Route route, Date start, Date end) {
        return reportRepository.findByRouteAndTimeSoldBetween(route,start,end);
    }

    @Override
    public List<Tickets> byTravelDate(Route route, Date travelDate) {
        return reportRepository.findByRouteAndDepartureDate(route,travelDate);
    }


}
