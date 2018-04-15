package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Agents;

import java.util.List;

public interface AgentService {
public List<Agents> findAll();
public Agents findByPhoneAndPin(String phone,String pin);
public Agents save(Agents agents);
public void delete(Integer id);
}
