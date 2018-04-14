package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.TicketService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TicketController {


    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TicketService ticketService;


@RequestMapping("/ticket/new")
    public ModelAndView ticket()
{
    ModelAndView modelAndView=new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user=userService.findByUsername(auth.getName());
    modelAndView.addObject("route",routeService.findAll());
    modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
    modelAndView.addObject("title","Tickets");
    modelAndView.addObject("tickets",new Tickets());
    modelAndView.setViewName("/ticket/ticket");
    return modelAndView;

}
@PostMapping("/ticket/new")
public String sell(@Valid Tickets tickets, RedirectAttributes model){
    System.out.println(tickets.getDepartureDate());
    System.out.println(ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate()));
    for(int i=0;i<tickets.getRoute().getBus().getSeats()-ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate());i++){
    if(ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate())<tickets.getRoute().getBus().getSeats())
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        tickets.setSoldBy(userService.findByUsername(authentication.getName()));
        ticketService.save(tickets);
        model.addFlashAttribute("error","success");
    }
    else
    {
    model.addFlashAttribute("error","error");}}
    return "redirect:/ticket/new";
}
}
