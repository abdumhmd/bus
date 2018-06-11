package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Bus;
import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.BusService;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RouteController {

    @Autowired
    private UserService userService;

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;

    @RequestMapping("/routes")
    public ModelAndView route()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("title","Routes");
    modelAndView.setViewName("/route/list");
    return modelAndView;

}
@RequestMapping(value = "/routes/new",method = RequestMethod.GET)
    public ModelAndView newRoute()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("bus",busService.findFree());
    modelAndView.addObject("route",new Route());
    modelAndView.addObject("title","Routes");
    modelAndView.setViewName("/route/route");
    return modelAndView;
}
@RequestMapping(value = "/routes/new", method = RequestMethod.POST)
public String saveBus(@Valid Route route,@Valid Bus bus, BindingResult bindingResult, RedirectAttributes model){
        busService.update(bus);
    routeService.save(route);
    model.addFlashAttribute("error","success");
    return "redirect:/routes/";
}
    @GetMapping("routes/delete/{id}")
    public String delete(@PathVariable int id) {
        Bus bus=busService.findById(routeService.findById(id).getBus().getId());
        bus.setAssigned(false);
        busService.save(bus);
        routeService.deleteById(id);
        return "redirect:/routes";
    }
}
