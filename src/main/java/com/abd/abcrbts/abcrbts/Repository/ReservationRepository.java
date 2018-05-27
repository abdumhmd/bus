package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Reservation;
import com.abd.abcrbts.abcrbts.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation,Long>{
    public Reservation save(Reservation reservation);
    public Reservation findByRefNumber(String refNum);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE reservation SET  paid= 1 WHERE ref_number=:refNum", nativeQuery = true)
    @Transactional
    public void updatePaid(@Param("refNum") String refNum);
    public Integer countReservationByRouteAndDepartureDate(Route route, Date date);
}
