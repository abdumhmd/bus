package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
@RequestMapping("/routes")
    public String route()
{
    return "route/list";
}
@RequestMapping("/routes/new")
    public String newRoute()
{
    return "route/route";
}
}
