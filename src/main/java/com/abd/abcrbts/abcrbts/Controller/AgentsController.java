package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgentsController {

    @RequestMapping("/agents")
    public ModelAndView agents()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("title","Agents");
        modelAndView.setViewName("/agents/list");
        return modelAndView;
    }
    @RequestMapping("/agents/new")
    public ModelAndView newAgent()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("title","Agents");
        modelAndView.setViewName("/agents/agents");
        return modelAndView;
    }
}
