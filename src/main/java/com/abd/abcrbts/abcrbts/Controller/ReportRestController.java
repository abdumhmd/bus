package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "reports/agentdata",method = RequestMethod.POST)
    public List<Tickets> agentdata(@RequestParam("agent") Agents agent, @RequestParam("start") Date start, @RequestParam("end") Date end)
    {
        return reportService.byAgent(agent,start,end);
    }
}
