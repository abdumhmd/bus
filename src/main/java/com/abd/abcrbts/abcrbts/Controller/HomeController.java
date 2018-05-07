package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Role;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.AgentService;
import com.abd.abcrbts.abcrbts.Service.TicketService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private AgentService agentService;

    @RequestMapping("/home")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUsername(auth.getName());
       String roles="";
       for(Role role: user.getRoles())
       {
            roles=role.getRole();
;           System.out.println(role.getRole());

       }
        if ("TICKET_OFFICER".equals(roles)) {
            return "redirect:/ticket/new";
        }
        else if ("ADMIN".equals(roles)){
            return "redirect:/routes/";}
        else {
            return "/login";
        }
    }

    @RequestMapping("/dashboard")
    public ModelAndView dash()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("ticket",ticketService.countAll());
        modelAndView.addObject("user",userService.findUsers().size());
        modelAndView.addObject("agent",agentService.findAll().size());
        modelAndView.addObject("title","Dashboard");
        modelAndView.setViewName("/dashboard");
        return modelAndView;
    }

}
