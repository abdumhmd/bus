package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Reservation;
import com.abd.abcrbts.abcrbts.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findByRefNum(String refNum) {
        return reservationRepository.findByRefNumber(refNum);
    }

    @Override
    public void updatePaid(String refNum) {
         reservationRepository.updatePaid(refNum);
    }
}
