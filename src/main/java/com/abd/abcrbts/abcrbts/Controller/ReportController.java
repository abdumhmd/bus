package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Role;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.AgentService;
import com.abd.abcrbts.abcrbts.Service.ReportService;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ReportController {
    @Autowired
    private UserService userService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private RouteService routeService;
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "reports/byagent",method = RequestMethod.GET)
    public ModelAndView byAgent()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("agents",agentService.findAll());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Report");
        modelAndView.setViewName("/Report/byagent");
        return modelAndView;
    }
    @RequestMapping(value = "reports/byticketofficer",method = RequestMethod.GET)
    public ModelAndView byTicketOfficer()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        Role role=new Role();
        role.setRole_id(2);
        modelAndView.addObject("agents",userService.findByRoles(role));
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Report");
        modelAndView.setViewName("/Report/byticketofficer");
        return modelAndView;
    }
    @RequestMapping(value = "reports/byroute",method = RequestMethod.GET)
    public ModelAndView byRoute()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("route",routeService.findAll());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Report");
        modelAndView.setViewName("/Report/byRoute");
        return modelAndView;
    }
    @RequestMapping(value = "reports/tickets",method = RequestMethod.GET)
    public ModelAndView ticketList()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("route",routeService.findAll());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Report");
        modelAndView.setViewName("/Report/bytraveldate");
        return modelAndView;
    }
    @PostMapping("/reports/byagent")
    public ModelAndView agentReport(@RequestParam("agent") String agent, @RequestParam("start") Date start, @RequestParam("end") Date end)
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.setViewName("report/agenttable");
        modelAndView.addObject("agent",agent);
        modelAndView.addObject("start",start);
        modelAndView.addObject("end",end);
        modelAndView.addObject("list",reportService.byAgent(agentService.findById(Integer.parseInt(agent)),start,end));
        System.out.println(reportService.byAgent(agentService.findById(Integer.parseInt(agent)),start,end).size());
        return modelAndView;
    }
    @PostMapping("/reports/byticketofficer")
    public ModelAndView ticketofficerReport(@RequestParam("user") String user, @RequestParam("start") Date start, @RequestParam("end") Date end)
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",users.getFirstName()+" "+users.getLastName());
        modelAndView.setViewName("report/officertable");
        modelAndView.addObject("user",user);
        modelAndView.addObject("start",start);
        modelAndView.addObject("end",end);
        modelAndView.addObject("list",reportService.byTicketOfficer(userService.findById(Integer.parseInt(user)),start,end));
        System.out.println(reportService.byTicketOfficer(userService.findById(Integer.parseInt(user)),start,end).size());
        return modelAndView;
    }
    @PostMapping("/reports/byroute")
    public ModelAndView routeReport(@RequestParam("route") String route, @RequestParam("start") Date start, @RequestParam("end") Date end){
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",users.getFirstName()+" "+users.getLastName());
        modelAndView.setViewName("report/routetable");
        modelAndView.addObject("route",route);
        modelAndView.addObject("start",start);
        modelAndView.addObject("end",end);
        modelAndView.addObject("list",reportService.byRoute(routeService.findById(Integer.parseInt(route)),start,end));
        System.out.println(reportService.byRoute(routeService.findById(Integer.parseInt(route)),start,end).size());
        return modelAndView;
    }
    @PostMapping("/reports/bytraveldate")
    public ModelAndView routeReport(@RequestParam("route") String route, @RequestParam("traveldate") Date traveldate){
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",users.getFirstName()+" "+users.getLastName());
        modelAndView.setViewName("report/passlist");
        modelAndView.addObject("route",route);
        modelAndView.addObject("start",traveldate);

        modelAndView.addObject("list",reportService.byTravelDate(routeService.findById(Integer.parseInt(route)),traveldate));
        System.out.println(reportService.byTravelDate(routeService.findById(Integer.parseInt(route)),traveldate).size());
        return modelAndView;
    }


}
