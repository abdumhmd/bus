package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgentsController {

    @Autowired
    private UserService userService;

    @RequestMapping("/agents")
    public ModelAndView agents()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Agents");
        modelAndView.setViewName("/agents/list");
        return modelAndView;
    }
    @RequestMapping("/agents/new")
    public ModelAndView newAgent()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("title","Agents");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.setViewName("/agents/agents");
        return modelAndView;
    }
}
