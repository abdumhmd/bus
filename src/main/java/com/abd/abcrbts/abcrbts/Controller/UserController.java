package com.abd.abcrbts.abcrbts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
@RequestMapping("/user")
public ModelAndView user(){
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","users");
    modelAndView.setViewName("/user/list");
    return modelAndView;

}
@RequestMapping("user/new")
public ModelAndView list()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","users");
    modelAndView.setViewName("/user/users");
    return modelAndView;

}
}
