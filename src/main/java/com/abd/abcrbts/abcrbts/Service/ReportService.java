package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;

import java.util.Date;
import java.util.List;

public interface ReportService {
    public List<Tickets> byAgent(Agents agent, Date start, Date end);
    public List<Tickets> byTicketOfficer(Users users,Date start,Date end);
    public List<Tickets> byRoute(Route route, Date start, Date end);
    public List<Tickets> byTravelDate(Route route,Date travelDate);


}
