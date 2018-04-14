package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Reservation;

public interface ReservationService {
    public Reservation save(Reservation reservation);
    public Reservation findByRefNum(String refNum);
    public void updatePaid(String refNum);

}
