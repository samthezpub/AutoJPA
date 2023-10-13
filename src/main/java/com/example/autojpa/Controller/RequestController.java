package com.example.autojpa.Controller;

import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Service.impl.CargoTypeServiceImpl;
import com.example.autojpa.Service.impl.DestinationServiceImpl;
import com.example.autojpa.Service.impl.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/requests")
public class RequestController {
    private final RequestServiceImpl requestService;
    private final DestinationServiceImpl destinationService;
    private final CargoTypeServiceImpl cargoTypeService;

    @Autowired
    public RequestController(RequestServiceImpl requestService, DestinationServiceImpl destinationService, CargoTypeServiceImpl cargoTypeService) {
        this.requestService = requestService;
        this.destinationService = destinationService;
        this.cargoTypeService = cargoTypeService;
    }

    @GetMapping("/get")
    public String getRequests(Model model){
        model.addAttribute("requests", requestService.findAll());

        return "requests/getRequest";
    }


    @GetMapping("/get/{id}")
    public String getRequest(Model model, @PathVariable("id") long id){

        model.addAttribute("request", requestService.findById(id).get());

        return "requests/getRequestById";
    }

    @GetMapping("/add")
    public String createRequest(Model model) {
        model.addAttribute("request", new RequestEntity());
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("types", cargoTypeService.findAll());

        return "requests/addRequest";
    }

    @PostMapping("/add/save")
    public RedirectView addSaveRequest(@ModelAttribute RequestEntity request) {

        request.setDone(false);
        requestService.saveRequest(request);

        return new RedirectView("/requests/get");
    }

    @GetMapping("/edit/{id}")
    public String editRequest(Model model, @PathVariable long id){
        model.addAttribute("request", requestService.findById(id).get());
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("types", cargoTypeService.findAll());

        return "requests/editRequest";
    }

    @PostMapping("/edit/save")
    public RedirectView editSaveRequest(@ModelAttribute RequestEntity request) {

        RequestEntity existRequest = requestService.findById(request.getId()).orElseThrow(RuntimeException::new);
        existRequest.setExperience(request.getExperience());
        existRequest.setDestination(request.getDestination());
        existRequest.setCargoType(request.getCargoType());
        existRequest.setDone(request.isDone());
        existRequest.setFree(request.isFree());

        requestService.updateRequest(existRequest);

        return new RedirectView("/requests/get");
    }

    @GetMapping(value = "/delete/{id}")
    public RedirectView deleteRequest(@PathVariable("id") long id){
        requestService.deleteById(id);


        return new RedirectView("/requests/get");
    }
}
