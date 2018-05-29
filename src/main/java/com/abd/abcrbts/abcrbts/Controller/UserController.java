package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Role;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.RoleService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import com.abd.abcrbts.abcrbts.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("title","users");
    modelAndView.setViewName("/user/list");
    return modelAndView;

}
@RequestMapping( value = "user/new",method = RequestMethod.GET)
public ModelAndView list()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
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
    @GetMapping("user/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/user";
    }
    @GetMapping("/changepass")
    public ModelAndView changepass() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/changepass");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());

        return modelAndView;
    }
    @PostMapping("/changepass")
    public String ChangePassword(RedirectAttributes model, @RequestParam("oldpass") String oldPassword, @RequestParam("newpass") String newPassword, @RequestParam("new2") String confirmPassword){
    String message=userService.changePassword(newPassword,oldPassword);
        if (message.equals("success")) {
            model.addFlashAttribute("error","success");
            return "redirect:/dashboard";

        } else {
            model.addFlashAttribute("error","error");
            return "redirect:/user/changePass";
        }
    }
}
