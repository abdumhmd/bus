package com.abd.abcrbts.abcrbts.Repository;

import com.abd.abcrbts.abcrbts.Model.Agents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agents,Integer>{
    public List<Agents> findAll();
    public Agents save(Agents agents);
    public Agents findAgentsByPhoneNumberAndPin(String phone,String pin);
}
