package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentsController {

    @RequestMapping("/agents")
    public String agents()
    {
        return "agents/list";
    }
    @RequestMapping("/agents/new")
    public String newAgent()
    {
        return "agents/agents";
    }
}
