package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Reservation;
import com.abd.abcrbts.abcrbts.Model.Route;

import java.util.Date;

public interface ReservationService {
    public Reservation save(Reservation reservation);
    public Reservation findByRefNum(String refNum);
    public void updatePaid(String refNum);
    public Integer countByRouteAndDepartureDate(Route route, Date date);

}
