package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.ReservationService;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.TicketService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class TicketController {


    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TicketService ticketService;

    @Autowired
            private ReservationService reservationService;

    Tickets tickets1;

    String num;

@GetMapping("/ticket/new")
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
    System.out.println(tickets.getRoute().getDeparture());
    System.out.println(ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate()));

    if(ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate())+reservationService.countByRouteAndDepartureDate(tickets.getRoute(),tickets.getDepartureDate())<tickets.getRoute().getBus().getSeats())
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
    model.addFlashAttribute("error","error");}
    return "redirect:/ticket/new";
}
@RequestMapping(value = "/ticket/reciept")
    public ModelAndView reciept()
{
    DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("ticket/reciept");
    modelAndView.addObject("error","success");
    modelAndView.addObject("departureDate",dateFormat.format(tickets1.getDepartureDate()));
    modelAndView.addObject("time",tickets1.getRoute().getTime());
    modelAndView.addObject("departureCity",tickets1.getRoute().getDeparture());
    modelAndView.addObject("destinationCity",tickets1.getRoute().getDestination());
    modelAndView.addObject("price",tickets1.getRoute().getPrice());
    modelAndView.addObject("seat",ticketService.countByRouteAndDate(tickets1.getRoute(),tickets1.getDepartureDate()));
    modelAndView.addObject("soldBy",tickets1.getSoldBy().getFirstName()+" "+tickets1.getSoldBy().getLastName());
    modelAndView.addObject("plate",tickets1.getRoute().getBus().getPlate());
    modelAndView.addObject("name",tickets1.getPassengerName());
    modelAndView.addObject("phone",tickets1.getPassengerPhone());
    return modelAndView;
}
    @RequestMapping(value = "/ticket/freciept")
    public ModelAndView freciept()
    {
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("ticket/freciept");
        modelAndView.addObject("error","success");
        modelAndView.addObject("departureDate",dateFormat.format(tickets1.getDepartureDate()));
        modelAndView.addObject("time",tickets1.getRoute().getTime());
        modelAndView.addObject("departureCity",tickets1.getRoute().getDeparture());
        modelAndView.addObject("destinationCity",tickets1.getRoute().getDestination());
        modelAndView.addObject("price",tickets1.getRoute().getPrice());
        modelAndView.addObject("Fseat",ticketService.countByRouteAndDate(tickets1.getRoute(),tickets1.getDepartureDate())-(Integer.parseInt(num)-1));
        modelAndView.addObject("Lseat",ticketService.countByRouteAndDate(tickets1.getRoute(),tickets1.getDepartureDate()));
        modelAndView.addObject("soldBy",tickets1.getSoldBy().getFirstName()+" "+tickets1.getSoldBy().getLastName());
        modelAndView.addObject("plate",tickets1.getRoute().getBus().getPlate());

        modelAndView.addObject("amount",num);
        modelAndView.addObject("name",tickets1.getPassengerName());
        modelAndView.addObject("phone",tickets1.getPassengerPhone());
        return modelAndView;
    }
    @GetMapping("/ticket/family")
    public ModelAndView family()
    {

        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("route",routeService.findAll());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Tickets");
        modelAndView.addObject("tickets",new Tickets());
        modelAndView.setViewName("/ticket/family");
        return modelAndView;

    }
    @PostMapping("/ticket/family")
    public String sellFamily(@Valid Tickets tickets, RedirectAttributes model, @RequestParam("amount") String amount)  {

        num=amount;
        System.out.println(tickets.getRoute().getDeparture());
        int seat=ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate());
        System.out.println(amount);

        if(ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate())+Integer.parseInt(amount)+reservationService.countByRouteAndDepartureDate(tickets.getRoute(),tickets.getDepartureDate())<=tickets.getRoute().getBus().getSeats())
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            tickets.setSoldBy(userService.findByUsername(authentication.getName()));
            for(int i=0;i<Integer.parseInt(amount);i++)
            {
                ticketService.save(new Tickets(tickets.getAgents(),tickets.getPassengerPhone(),tickets.getPassengerName(),tickets.getRoute(),tickets.getSoldBy(),tickets.getDepartureDate()));

            }




            model.addFlashAttribute("error","success");
            model.addFlashAttribute("departureDate",tickets.getDepartureDate());
            model.addFlashAttribute("time",tickets.getRoute().getTime());
            model.addFlashAttribute("departureCity",tickets.getRoute().getDeparture());
            model.addFlashAttribute("destinationCity",tickets.getRoute().getDestination());
            model.addFlashAttribute("price",tickets.getRoute().getPrice());
            model.addFlashAttribute("seat",seat);
            model.addFlashAttribute("soldBy",tickets.getSoldBy());
            model.addFlashAttribute("plate",tickets.getRoute().getBus().getPlate());
            tickets1=tickets;

            System.out.println(ticketService.countByRouteAndDate(tickets.getRoute(),tickets.getDepartureDate()));
        }
        else
        {
            model.addFlashAttribute("error","error");}
        return "redirect:/ticket/family";
    }
}
