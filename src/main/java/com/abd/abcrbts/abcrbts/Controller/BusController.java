package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Bus;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.BusService;
import com.abd.abcrbts.abcrbts.Service.UserService;
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
public class BusController {

    @Autowired
    private UserService userService;

    @Autowired
    private BusService busService;

@RequestMapping("/buses")
    public ModelAndView bus()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("title","Buses");
    modelAndView.setViewName("/buses/list");
    return modelAndView;
}
@RequestMapping(value = "/buses/new" ,method= RequestMethod.GET)
    public ModelAndView newBus()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    modelAndView.addObject("bus",new Bus());
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("title","Buses");
    modelAndView.setViewName("/buses/bus");
    return modelAndView;

}
@RequestMapping(value = "buses/new",method = RequestMethod.POST)
    public String saveBus(@Valid Bus bus, BindingResult bindingResult, RedirectAttributes model){
    busService.save(bus);
    model.addFlashAttribute("error","success");
    return "redirect:/buses/";
}
    @GetMapping("buses/delete/{id}")
    public String delete(@PathVariable int id) {
        busService.deleteById(id);
        return "redirect:/buses";
    }
}
