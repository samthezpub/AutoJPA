package com.example.autojpa.Controller;

import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Service.impl.AutoServiceImpl;
import com.example.autojpa.Service.impl.DriverServiceImpl;
import com.example.autojpa.Service.impl.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/drivers")
public class DriverController {
    private final DriverServiceImpl driverService;
    private final AutoServiceImpl autoService;
    private final RequestServiceImpl requestService;

    @Autowired
    public DriverController(DriverServiceImpl driverService, AutoServiceImpl autoService, RequestServiceImpl requestService) {
        this.driverService = driverService;
        this.autoService = autoService;
        this.requestService = requestService;
    }

    @GetMapping("/get")
    public String getDrivers(Model model){
        model.addAttribute("drivers", driverService.findAll());

        return "driver/getDriver";
    }


    @GetMapping("/get/{id}")
    public String getDriver(Model model, @PathVariable("id") long id){

        model.addAttribute("driver", driverService.findById(id).get());

        return "driver/getDriverById";
    }

    @GetMapping("/add")
    public String createDriver(Model model) {
        model.addAttribute("driver", new DriverEntity());
        return "driver/addDriver";
    }

    @PostMapping("/add/save")
    public RedirectView addSaveDriver(@ModelAttribute DriverEntity driver) {

        driver.setMoney(0.0);
        driverService.saveDriver(driver);

        return new RedirectView("/drivers/get");
    }

    @GetMapping("/edit/{id}")
    public String editDriver(Model model, @PathVariable long id){
        model.addAttribute("driver", driverService.findById(id).get());
        model.addAttribute("autos", autoService.findAll());
        model.addAttribute("requests", requestService.findAll());

        return "driver/editDriver";
    }

    @PostMapping("/edit/save")
    public RedirectView editSaveAuto(@ModelAttribute DriverEntity driver) {

        DriverEntity existDriver = driverService.findById(driver.getId()).orElseThrow(RuntimeException::new);
        existDriver.setName(driver.getName());
        existDriver.setExperience(driver.getExperience());
        existDriver.setAuto(driver.getAuto());
        existDriver.setMoney(driver.getMoney());
        existDriver.setRequest(driver.getRequest());
        existDriver.setFree(driver.isFree());

        driverService.updateDriver(driver);

        return new RedirectView("/drivers/get");
    }

    @GetMapping(value = "/delete/{id}")
    public RedirectView deleteDriver(@PathVariable("id") long id){
        driverService.deleteById(id);


        return new RedirectView("/drivers/get");
    }
}
