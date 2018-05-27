package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Tickets,Long> {
    @Query("SELECT t FROM Tickets t WHERE t.agents=(:agent) AND (:start='' AND :end='' or t.timeSold BETWEEN :start AND :end )")
    List<Tickets> findAll1(@Param("agent")Agents agent,@Param("start") String start,@Param("end") String end);
    @Query("SELECT t FROM Tickets t WHERE t.agents=(:agent)")
    List<Tickets> findAll(@Param("agent")Agents agent);
}
