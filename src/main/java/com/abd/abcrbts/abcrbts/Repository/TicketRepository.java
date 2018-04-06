package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TicketRepository extends JpaRepository<Tickets,Long>{

    public Tickets save(Tickets tickets);
    public Integer countTicketsByTimeSold(Date date);
    public Integer countTicketsBySoldBy(Users users);
    public Integer countTicketsByRouteAndDepartureDate(Route route,Date date);
}
