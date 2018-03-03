package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BusController {
@RequestMapping("/buses")
    public String bus()
{
    return "/buses/list";
}
@RequestMapping("/buses/new")
    public String newBus()
{
    return "/buses/bus";
}
}
