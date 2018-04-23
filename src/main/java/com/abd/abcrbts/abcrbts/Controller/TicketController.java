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

    Tickets tickets1;

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
        model.addFlashAttribute("departureDate",tickets.getDepartureDate());
        model.addFlashAttribute("time",tickets.getRoute().getTime());
        model.addFlashAttribute("departureCity",tickets.getRoute().getDeparture());
        model.addFlashAttribute("destinationCity",tickets.getRoute().getDestination());
        model.addFlashAttribute("price",tickets.getRoute().getPrice());
        model.addFlashAttribute("seat",ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate())+1);
        model.addFlashAttribute("soldBy",tickets.getSoldBy());
        model.addFlashAttribute("plate",tickets.getRoute().getBus().getPlate());
        tickets1=tickets;
    }
    else
    {
    model.addFlashAttribute("error","error");}}
    return "redirect:/ticket/new";
}
@RequestMapping(value = "/ticket/reciept")
    public ModelAndView reciept()
{
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("ticket/reciept");
    modelAndView.addObject("error","success");
    modelAndView.addObject("departureDate",tickets1.getDepartureDate());
    modelAndView.addObject("time",tickets1.getRoute().getTime());
    modelAndView.addObject("departureCity",tickets1.getRoute().getDeparture());
    modelAndView.addObject("destinationCity",tickets1.getRoute().getDestination());
    modelAndView.addObject("price",tickets1.getRoute().getPrice());
    modelAndView.addObject("seat",ticketService.countByRouteAndDate(tickets1.getRoute(),tickets1.getDepartureDate())+1);
    modelAndView.addObject("soldBy",tickets1.getSoldBy().getFirstName()+" "+tickets1.getSoldBy().getLastName());
    modelAndView.addObject("plate",tickets1.getRoute().getBus().getPlate());
    modelAndView.addObject("name",tickets1.getPassengerName());
    modelAndView.addObject("phone",tickets1.getPassengerPhone());
    return modelAndView;
}
}
