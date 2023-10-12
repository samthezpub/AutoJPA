package com.example.autojpa.Controller;

import com.example.autojpa.Service.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.Thymeleaf;

@Controller
public class DriverController {
    private final DriverServiceImpl driverService;

    public DriverController(DriverServiceImpl driverService) {
        this.driverService = driverService;
    }

    @GetMapping("driver/get")
    public String getDrivers(Model model){
        model.addAttribute("drivers", driverService.findAll());

        return "driver/get";
    }


    public String addDriver(Model model){

        return "driver/add";
    }
}
