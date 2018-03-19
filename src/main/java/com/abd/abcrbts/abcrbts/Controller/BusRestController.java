package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Bus;
import com.abd.abcrbts.abcrbts.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BusRestController {
    @Autowired
    private BusService busService;

    @RequestMapping(value = "/bus/table", method = RequestMethod.GET)
    public List<Bus> findList()
    {
        return busService.findAll();
    }
}
