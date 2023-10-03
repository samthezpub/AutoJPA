package com.example.autojpa;

import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Exception.NotFindException;
import com.example.autojpa.Service.RequestService;
import com.example.autojpa.Service.impl.DriverServiceImpl;
import com.example.autojpa.Service.impl.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.NotActiveException;
import java.util.List;

@Component
public class Dispatch {
    private final DriverServiceImpl driverService;

    private final RequestServiceImpl requestService;

    public Dispatch(ApplicationContext applicationContext) {
        this.driverService = applicationContext.getBean(DriverServiceImpl.class);
        this.requestService = applicationContext.getBean(RequestServiceImpl.class);
    }


    private RequestEntity getNotDoneRequest(){
        List<RequestEntity> requests;
        try {
            requests = requestService.findAllNotDoneRequests().orElseThrow(()-> new NotFindException("Нет входящих запросов!"));
        } catch (NotFindException e) {
            throw new RuntimeException(e);
        }

        if (requests.isEmpty()) {
            throw new RuntimeException("Список запросов пуст!");
        }

        return requests.get(0);
    }
    public void setDriverForRequest(){

        List<DriverEntity> drivers;
        RequestEntity request = getNotDoneRequest();

        drivers = driverService.findAll();

        for (DriverEntity driver : drivers) {
            if (driver.getExperience() >= request.getExperience()) {
                driverService.updateDriverRequestById(request, driver.getId());
            }
            break;
        }
    }

}
