package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {
@RequestMapping("/ticket/new")
    public String ticket()
{
    return "ticket/ticket";
}
}
