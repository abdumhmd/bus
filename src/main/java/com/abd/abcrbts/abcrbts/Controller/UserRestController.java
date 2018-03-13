package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {
@Autowired
    private UserService userService;
@RequestMapping(value = "/users/table",method = RequestMethod.GET, produces = "application/JSON")
public List<Users> forTable()
{
return userService.findUsers();
}
}
