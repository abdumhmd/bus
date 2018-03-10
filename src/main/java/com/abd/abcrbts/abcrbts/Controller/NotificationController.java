package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotificationController {

    @RequestMapping("/notifications")
    public ModelAndView notification()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("title","Notifications");
        modelAndView.setViewName("/Notifications/notification");
        return modelAndView;
    }
}
