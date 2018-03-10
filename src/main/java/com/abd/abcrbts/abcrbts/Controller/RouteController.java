package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {
@RequestMapping("/routes")
    public ModelAndView route()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","Routes");
    modelAndView.setViewName("/route/list");
    return modelAndView;

}
@RequestMapping("/routes/new")
    public ModelAndView newRoute()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","Routes");
    modelAndView.setViewName("/route/route");
    return modelAndView;

}
}
