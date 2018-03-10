package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BusController {

@RequestMapping("/buses")
    public ModelAndView bus()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","Buses");
    modelAndView.setViewName("/buses/list");
    return modelAndView;
}
@RequestMapping("/buses/new")
    public ModelAndView newBus()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","Buses");
    modelAndView.setViewName("/buses/bus");
    return modelAndView;

}
}
