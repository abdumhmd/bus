package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.RoleService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import com.abd.abcrbts.abcrbts.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login()
    {
        Users users=new Users();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("login");

        return modelAndView;
    }
}
