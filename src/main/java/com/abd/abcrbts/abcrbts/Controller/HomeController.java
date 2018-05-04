package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Role;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUsername(auth.getName());
       String roles="";
       for(Role role: user.getRoles())
       {
            roles=role.getRole();
;           System.out.println(role.getRole());

       }
        if ("TICKET_OFFICER".equals(roles)) {
            return "/routes/new";
        }
        else if ("ADMIN".equals(roles)){
        return "/route/route";}
        else {
            return "/login";
        }
    }

}
