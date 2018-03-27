package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicketController {


    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;
@RequestMapping("/ticket/new")
    public ModelAndView ticket()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("route",routeService.findAll());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("title","Tickets");
    modelAndView.setViewName("/ticket/ticket");
    return modelAndView;

}
}
