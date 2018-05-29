package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.MyOzSmsClient;
import com.abd.abcrbts.abcrbts.Service.ReportService;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.TicketService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ReportService reportService;

    @RequestMapping("/notifications")
    public ModelAndView notification()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("route",routeService.findAll());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Notifications");
        modelAndView.setViewName("/Notifications/notification");
        return modelAndView;
    }
    @PostMapping("/notifications")
    public String sendNotification(RedirectAttributes model, @RequestParam("route") Route route, @RequestParam("date") Date date, @RequestParam("message") String message)
    {
        MyOzSmsClient myOzSmsClient= null;
        try {
            myOzSmsClient = new MyOzSmsClient("localhost",9500);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            myOzSmsClient.login("admin","abc123");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(myOzSmsClient.isLoggedIn())
        {
            List<Tickets> receipients=reportService.byTravelDate(route,date);
            for(int i=0;i<receipients.size();i++)
            {
                try {
                    myOzSmsClient.sendMessage(receipients.get(i).getPassengerPhone(),message+"\n");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        return "redirect:/notifications/";
    }
}
