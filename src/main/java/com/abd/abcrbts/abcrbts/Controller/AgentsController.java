package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.AgentService;
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
import java.util.List;

@Controller
public class AgentsController {

    @Autowired
    private UserService userService;

    @Autowired
    private AgentService agentService;

    @RequestMapping("/agents")
    public ModelAndView agents()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("title","Agents");
        modelAndView.setViewName("/agents/list");
        return modelAndView;
    }
    @RequestMapping(value = "/agents/new",method = RequestMethod.GET)
    public ModelAndView newAgent()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("title","Agents");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=userService.findByUsername(auth.getName());
        modelAndView.addObject("fullname",user.getFirstName()+" "+user.getLastName());
        modelAndView.addObject("agent",new Agents());
        modelAndView.setViewName("/agents/agents");
        return modelAndView;
    }
    @RequestMapping(value = "/agents/new",method = RequestMethod.POST)
    public String saveAgent(@Valid Agents agent, BindingResult bindingResult, RedirectAttributes model)
    {
        agent.setPhoneNumber("00"+agent.getPhoneNumber().substring(4));
        agentService.save(agent);
        model.addFlashAttribute("error","success");
        model.addFlashAttribute("pin",agent.getPin());
        return "redirect:/agents/";
    }

    @GetMapping("agents/delete/{id}")
    public String delete(@PathVariable int id) {
        agentService.delete(id);
        return "redirect:/agents";
    }

}
