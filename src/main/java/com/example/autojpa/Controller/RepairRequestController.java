package com.example.autojpa.Controller;

import com.example.autojpa.Entity.RepairRequestEntity;
import com.example.autojpa.Service.impl.RepairRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/repair")
public class RepairRequestController {
    private final RepairRequestServiceImpl repairRequestService;

    @Autowired
    public RepairRequestController(RepairRequestServiceImpl repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @GetMapping("/get")
    public String getRepairRequests(Model model){
        model.addAttribute("repairRequests", repairRequestService.findAll());

        return "repairRequests/getRepairRequests";
    }

    @GetMapping("/delete/{id}")
    public String deleteRepairRequest(Model model,@PathVariable("id") long id){
        model.addAttribute("id", id);
        return "repairRequests/deleteRepairRequest";
    }

    @PostMapping("/delete")
    public RedirectView deleteRepairConfirmRequest(long id){
        repairRequestService.deleteRequestById(id);

        return new RedirectView("/repair/get");
    }
}
