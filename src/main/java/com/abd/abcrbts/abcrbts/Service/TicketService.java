package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;

import java.util.Date;

public interface TicketService {
    public Tickets save(Tickets ticket);
    public Integer countByDateSold(Date dateSold);
    public Integer countBySoldBy(Users users);
    public Integer countByRouteAndDate(Route route,Date departureDate);

}
