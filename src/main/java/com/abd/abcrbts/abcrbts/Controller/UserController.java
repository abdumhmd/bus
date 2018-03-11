package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Role;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.RoleService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import com.abd.abcrbts.abcrbts.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
@RequestMapping( value = "user/new",method = RequestMethod.GET)
public ModelAndView list()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("title","users");
    Users users=new Users();
    Role role=new Role();
    modelAndView.addObject("roles",roleService.findAll());
    modelAndView.addObject("users",users);
    modelAndView.addObject("role",role);
    modelAndView.setViewName("/user/users");
    return modelAndView;

}
    @RequestMapping(value = "user/new",method = RequestMethod.POST)
    public String newRecord(@Valid Users users, BindingResult bindingResult, @RequestParam("role") Long role, RedirectAttributes model){

        userService.save(users);

        userService.addRole(users.getId(),role.intValue());
        model.addFlashAttribute("error", "success");

        model.addFlashAttribute("pass",users.getPassword());
        model.addFlashAttribute("uname",users.getUsername());
        return "redirect:/user/";

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
