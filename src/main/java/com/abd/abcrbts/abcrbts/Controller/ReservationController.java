package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Reservation;
import com.abd.abcrbts.abcrbts.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @RequestMapping(value = "/reserve",method = RequestMethod.GET)
    public void reservation(String reservation)
    {
        Reservation reservation1=new Reservation();
        reservation1.setPassengerphone(reservation);
        reservationService.save(reservation1);
    }
}
