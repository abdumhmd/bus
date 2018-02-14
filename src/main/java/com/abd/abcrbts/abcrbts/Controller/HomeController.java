package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home()
    {
        return "layout";
    }
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
}
