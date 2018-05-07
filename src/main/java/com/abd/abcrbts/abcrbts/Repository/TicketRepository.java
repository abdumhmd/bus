package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Tickets,Long>{

    public Tickets save(Tickets tickets);
    public Integer countTicketsByTimeSold(Date date);
    public Integer countTicketsBySoldBy(Users users);
    public Integer countTicketsByRouteAndDepartureDate(Route route,Date date);
    public Tickets findTicketsById(Long id);
    public List<Tickets> findAll();
    @Query(value = "\n" +
            "select (select count(*) from tickets where month(departure_date)=1),(select count(*) from tickets where month(departure_date)=2),(select count(*) from tickets where month(departure_date)=3),(select count(*) from tickets where month(departure_date)=4),(select count(*) from tickets where month(departure_date)=5),(select count(*) from tickets where month(departure_date)=6),(select count(*) from tickets where month(departure_date)=7),(select count(*) from tickets where month(departure_date)=8),(select count(*) from tickets where month(departure_date)=9),(select count(*) from tickets where month(departure_date)=10),(select count(*) from tickets where month(departure_date)=11),(select count(*) from tickets where month(departure_date)=12)",nativeQuery = true)
    public List<Object> monthly();
}
