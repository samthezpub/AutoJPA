package com.example.autojpa.Controller;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Service.impl.AutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/add")
    public String createAuto(Model model) {
        model.addAttribute("car", new AutoEntity());
        return "cars/addCar";
    }

    @PostMapping("/add/save")
    public RedirectView addSaveAuto(@ModelAttribute AutoEntity auto) {
        auto.setFree(true);
        autoService.saveAuto(auto);

        return new RedirectView("/cars/get");
    }

    @GetMapping("/edit/{id}")
    public String editAuto(Model model, @PathVariable long id){
        model.addAttribute("car", autoService.findById(id).get());

        return "cars/editAuto";
    }

    @PostMapping("/edit/save")
    public RedirectView editSaveAuto(@ModelAttribute AutoEntity auto) {

        AutoEntity existAuto = autoService.findById(auto.getId()).orElseThrow(RuntimeException::new);
        existAuto.setCargoType(auto.getCargoType());
        existAuto.setCargoQuantity(auto.getCargoQuantity());
        existAuto.setFree(auto.isFree());

        autoService.updateAuto(existAuto);

        return new RedirectView("/cars/get");
    }

    @GetMapping(value = "/delete/{id}")
    public RedirectView deleteCar(@PathVariable("id") long id){
        autoService.deleteById(id);


        return new RedirectView("/cars/get");
    }
}
