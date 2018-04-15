package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentRestController {
    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "/agents/table", method = RequestMethod.GET)
    public List<Agents> findList()
    {
        return agentService.findAll();
    }
}
