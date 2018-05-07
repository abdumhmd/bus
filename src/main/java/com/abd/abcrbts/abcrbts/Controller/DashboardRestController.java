package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardRestController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/monthly")
    public List<Object> monthly()
    {
        return ticketService.monthly();
    }
}
