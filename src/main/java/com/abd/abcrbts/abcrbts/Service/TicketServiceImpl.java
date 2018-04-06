package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Tickets save(Tickets ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Integer countByDateSold(Date dateSold) {
        return ticketRepository.countTicketsByTimeSold(dateSold);
    }

    @Override
    public Integer countBySoldBy(Users users) {
        return ticketRepository.countTicketsBySoldBy(users);
    }

    @Override
    public Integer countByRouteAndDate(Route route, Date departureDate) {
        return ticketRepository.countTicketsByRouteAndDepartureDate(route,departureDate);
    }
}
