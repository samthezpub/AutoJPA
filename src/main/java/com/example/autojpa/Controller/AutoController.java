package com.example.autojpa.Controller;

import com.example.autojpa.Service.impl.AutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class AutoController {
    private final String GET_CARS_ENDPOINT = "/cars/get";
    private final String GET_CARS_VIEW = "getcars";


    @Autowired
    private final AutoServiceImpl autoService;

    public AutoController(AutoServiceImpl autoService) {
        this.autoService = autoService;
    }

    @GetMapping(value = "get")
    public String getCars(Model model) {
        model.addAttribute("cars", autoService.findAll());

        return "cars/getCar";
    }

    @GetMapping("get/{id}")
    public String getCar(Model model, @PathVariable("id") long id){

        model.addAttribute("car", autoService.findById(id));

        return "cars/getCarById";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addCars(Model model) {
        model.addAttribute("cars", autoService.findAll());

        return "get";
    }
}
