package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Repository.AgentRepository;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Override
    public List<Agents> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agents findByPhoneAndPin(String phone, String pin) {
        return agentRepository.findAgentsByPhoneNumberAndPin(phone,pin);
    }


    @Override
    public Agents save(Agents agents) {

        agents.setArea(agents.getArea());
        agents.setFirstName(agents.getFirstName());
        agents.setLastName(agents.getLastName());
        agents.setPhoneNumber(agents.getPhoneNumber());
        agents.setPin(UUID.randomUUID().toString().substring(0,4));
        return agentRepository.save(agents);
    }
}
