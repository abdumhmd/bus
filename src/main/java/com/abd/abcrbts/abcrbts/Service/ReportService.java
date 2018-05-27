package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Tickets;

import java.util.List;

public interface ReportService {
    public List<Tickets> byAgent(Agents agent,String start,String end);


}
